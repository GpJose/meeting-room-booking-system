package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.factory.UserFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.repository.user.UserRepositoryInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.UserServiceInterface;
import gp.jose.practice.reserveration.meetindAndBooking.utils.CryptUtil;

import java.util.Optional;

public class UserServiceImp <U extends UserInterface> implements UserServiceInterface<U> {

    private final UserRepositoryInterface<U> userRepositoryInterface;
    private final UserFactory<U> userFactory;

    public UserServiceImp(UserRepositoryInterface<U> userRepositoryInterface,
                          UserFactory<U> userFactory) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.userFactory = userFactory;
    }

    @Override
    public Optional<U> auth(String login, String password) {

        return userRepositoryInterface
                .findAll()
                .stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(login))
                .filter(u -> CryptUtil.checkHash(password, u.getPassword()))
                .findFirst();
    }

    @Override
    public void create(String fio, String login, String password) {

        boolean exists = userRepositoryInterface.findAll().stream()
                .anyMatch(u -> u.getLogin().equalsIgnoreCase(login));

        if (exists) {
            System.out.println("Пользователь с таким логином уже существует");
            return;
        }

        U newUser = userFactory.create(fio, login, CryptUtil.hash(password));

        try {

            userRepositoryInterface.save(newUser);
            System.out.printf("Пользователь с логином %s создан%n", login);

        } catch (Exception e) {
            System.out.printf("Ошибка: не удалось создать пользователя с логином %s%n", login);
            throw e;
        }
    }
}
