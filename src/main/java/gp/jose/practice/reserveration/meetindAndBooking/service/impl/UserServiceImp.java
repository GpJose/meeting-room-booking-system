package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.factory.UserFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.repository.UserRepositoryInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.UserServiceInterface;
import gp.jose.practice.reserveration.meetindAndBooking.utils.CryptUtil;

import java.util.Optional;

public class UserServiceImp <U extends UserInterface> implements UserServiceInterface<U> {

    private final UserRepositoryInterface<U> userRepositoryInterface;
    private final UserFactory<U> userFactory;

    private U user;

    public UserServiceImp(UserRepositoryInterface<U> userRepositoryInterface,
                          UserFactory<U> userFactory) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.userFactory = userFactory;
    }

    @Override
    public boolean auth(String login, String password) {

        Optional<U> foundUser = userRepositoryInterface.findAll().stream()
                .filter(u -> u.getUserLogin().equalsIgnoreCase(login))
                .filter(u -> CryptUtil.checkHash(password, u.getPassword()))
                .findFirst();

        foundUser.ifPresent(u -> this.user = u);

        return foundUser.isPresent();
    }

    @Override
    public boolean create(String fio, String login, String password) {

        boolean exists = userRepositoryInterface.findAll().stream()
                .anyMatch(u -> u.getUserLogin().equalsIgnoreCase(login));

        if (exists) {
            System.out.println("Пользователь с таким логином уже существует");
            return false;
        }

        U newUser = userFactory.create(fio, login, CryptUtil.hash(password));

        boolean isCreated = userRepositoryInterface.create(newUser);
        if (isCreated) {
            System.out.printf("Пользователь с логином %s создан%n", login);
        } else {
            System.out.printf("Ошибка: не удалось создать пользователя с логином %s%n", login);
        }

        return isCreated;
    }

    @Override
    public U getUser() {
        return this.user;
    }
}
