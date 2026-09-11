package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.factory.RoomFactory;
import gp.jose.practice.reserveration.meetindAndBooking.factory.UserFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.ActionsEnum;
import gp.jose.practice.reserveration.meetindAndBooking.repository.room.RoomsRepository;
import gp.jose.practice.reserveration.meetindAndBooking.repository.user.UsersRepository;
import gp.jose.practice.reserveration.meetindAndBooking.service.ControlInterface;

import java.util.Optional;
import java.util.Scanner;

import static gp.jose.practice.reserveration.meetindAndBooking.utils.InputUtil.*;

public class ControlService <U extends UserInterface, R extends RoomInterface> implements ControlInterface {

    private final UserServiceImp<U> userService;
    private final RoomServiceImpl<R> roomService;
    private U user;
    private boolean isAuthorized;

    public ControlService(UserFactory<U> userFactory, RoomFactory<R> roomFactory) {
        this.userService = new UserServiceImp<>(new UsersRepository<>(), userFactory);
        this.roomService = new RoomServiceImpl<>(new RoomsRepository<>(roomFactory));
        this.isAuthorized = false;
        this.user = null;
    }

    public void printMenu() {

        StringBuilder stringBuilder = new StringBuilder();

        if(isAuthorized(false)) stringBuilder.append("Вы авторизованы. Login : ")
                .append(getUser().login()).append("\n");
        else stringBuilder.append("Вы не авторизованы\n");

        stringBuilder.append(
                """
                Action list :
                """
        ).append(ActionsEnum.consoleNavigation(isAuthorized(false)));
        System.out.println(stringBuilder);
    }

    public void action(ActionsEnum action, Scanner in) {

        if (action.isAuthRequired()) {
            if(! isAuthorized(true)) return;
        }
        switch (action) {

            case LOGOUT -> {
                setUser(null);
                System.out.println("Logout...");
            }
            case AUTH -> auth(enterLogin(in), enterPassword(in))
                    .ifPresent(this::setUser);

            case CREATE_USER -> create(enterFio(in), enterLogin(in), enterPassword(in));

            case CREATE_BOOKING -> {
            }
            case CANCEL_BOOKING -> {
            }
            case FIND_BOOKING_BY_ROOM -> {
            }
            case FIND_ROOM_BY_NAME -> roomService.findByRoomName(enterRoomName(in));
            case FIND_ROOM_BY_CAPACITY -> {

            }
            case FIND_ROOM_BY_EQUIPMENTS -> {

            }
            default -> System.out.printf("Нет реализации для действия %s цифра %s \n", action, action.getCode());
        }
    }

    private Optional<U> auth(String login, String password) {
        System.out.println("Попытка авторизоваться");
        Optional<U> auth = userService.auth(login, password);

        setAuth(auth.isPresent());

        if(isAuthorized(false)) System.out.println("Авторизация успешна");
        else System.out.println("Не верные данные");
        return auth;
    }

    private void create(String fio, String login, String password) {
        userService.create(fio, login, password);
    }

    private void setAuth(boolean isAuth) {
        this.isAuthorized = isAuth;
    }

    private void setUser(U user) {
        if(user == null) {
            this.user = null;
            setAuth(false);
        }
        this.user = user;
    }
    private U getUser() {
        return this.user;
    }

    private boolean isAuthorized(boolean out) {
        if(! this.isAuthorized && out) {
            System.out.println("Для данного дейситвия авторизуйтесь или зарегистрируйтесь и авторизуйтесь" );
        }
        return this.isAuthorized;
    }
}
