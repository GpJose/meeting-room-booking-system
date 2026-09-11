package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.factory.UserFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.repository.UsersRepository;
import gp.jose.practice.reserveration.meetindAndBooking.service.ControlInterface;

import java.util.Scanner;

public class ControlService <U extends UserInterface> implements ControlInterface {

    private final UserServiceImp<U> userUserServiceImp;
    private U user;
    private boolean isAuthorized;

    public ControlService(UserFactory<U> userFactory) {
        this.userUserServiceImp = new UserServiceImp<>(new UsersRepository<>(), userFactory);
        this.isAuthorized = false;
    }
    public void printMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        if(isAuthorized) stringBuilder.append("Вы авторизованы. Login : ")
                .append(user.getUserLogin());
        else stringBuilder.append("Вы не авторизованы\n");

        stringBuilder.append(
                """
        Action list :
        1 - auth
        2 - createUser
        """
        );
        if(isAuthorized) {
            stringBuilder.append(
                    """
        3 - reserve room
        4 - findRoom
        0 - exit"""
            );
        }
        stringBuilder.append("""
                10 - close application""");
        System.out.println(stringBuilder);
    }

    /**
     * Action list :
     * 1 - auth();
     * 2 - createUser();
     * 3 - createBooking();
     * 4 - findRoom();
     * 0 - exit();
     * @param action
     */
    public void action(Integer action, Scanner in) {

        if(action == null) {
            System.out.println("Дейсвие не может быть null");
            return;
        }

        switch (action) {
            case 10 -> {}
            case 0 -> {
                this.user = null;
                this.isAuthorized = false;
            }
            case 1 -> {
            }
            case 2 -> {
                in.nextLine();
                System.out.println("Введите фио, логин и пароль");

                create(
                        in.nextLine(),
                        in.nextLine(),
                        in.nextLine());
            }
            case 3 -> {
                if(isAuthorized()) {

                }
            }
            case 4 -> {
                if(isAuthorized()) {
                }
            }
            default -> System.out.println("Не правильное действие");
        }
    }

    public Long getRoomId() {
        return 0L;
    }

    public String getRoomName() {
        return "";
    }

    private boolean auth(String login, String password) {
        System.out.println("Попытка авторизоваться");
        this.isAuthorized = userUserServiceImp.auth(login, password);
        if(isAuthorized) System.out.println("Авторизация успешна");
        else System.out.println("Не верные данные");
        return isAuthorized;
    }

    private void create(String fio, String login, String password) {
        userUserServiceImp.create(fio, login, password);
    }

    public U getUser() {
        return this.user;
    }

    private boolean isAuthorized() {
        if(! this.isAuthorized) {
            System.out.println("Вы не авторизованы.\nАвторизуйтесь или зарегистрируйтесь" );
        }
        return this.isAuthorized;
    }
}
