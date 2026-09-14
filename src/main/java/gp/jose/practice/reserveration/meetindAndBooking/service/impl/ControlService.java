package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.factory.RoomFactory;
import gp.jose.practice.reserveration.meetindAndBooking.factory.UserFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.ActionsEnum;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;
import gp.jose.practice.reserveration.meetindAndBooking.repository.room.RoomsRepository;
import gp.jose.practice.reserveration.meetindAndBooking.repository.user.UsersRepository;
import gp.jose.practice.reserveration.meetindAndBooking.service.BookingServiceInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.ControlInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.RoomServiceInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.UserServiceInterface;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static gp.jose.practice.reserveration.meetindAndBooking.utils.InputUtil.*;

public class ControlService <U extends UserInterface, R extends RoomInterface> implements ControlInterface {

    private final UserServiceInterface<U> userService;
    private final RoomServiceInterface<R> roomService;
    private final BookingServiceInterface bookingService;
    private U user;
    private final Map<String, R> rooms;
    private boolean isAuthorized;

    public ControlService(UserFactory<U> userFactory,
                          RoomFactory<R> roomFactory,
                          BookingServiceInterface bookingService) {
        this.userService = new UserServiceImp<>(new UsersRepository<>(), userFactory);
        this.roomService = new RoomServiceImpl<>(new RoomsRepository<>(roomFactory));
        this.bookingService = bookingService;
        this.isAuthorized = false;
        this.user = null;
        this.rooms = roomService.findAll();
    }

    public void printMenu() {

        StringBuilder stringBuilder = new StringBuilder();

        if(isAuthorized(false)) stringBuilder.append("Вы авторизованы. Login : ")
                .append(getUser().getLogin()).append("\n");
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
            case AUTH -> {

                if(this.user == null) {
                    auth(enterLogin(in), enterPassword(in))
                            .ifPresent(this::setUser);
                } else System.out.println("Вы уже авторизованы");

            }

            case CREATE_USER -> create(enterFio(in), enterLogin(in), enterPassword(in));

            case CREATE_BOOKING -> {
                bookingService.createMeeting(rooms.get(enterRoomName(in, getRoomNames())),
                        user,
                        enterLocalDate(in, true),
                        enterLocalDate(in, false));
            }
            case CANCEL_BOOKING -> {
                bookingService.cancelReserve(user, rooms.get(enterRoomName(in, getRoomNames())), enterBookingId(in));
            }
            case FIND_BOOKING_BY_ROOM -> {
                bookingService.findBookingByRoom(rooms.get(enterRoomName(in, getRoomNames())), true);
            }

            case FIND_ALL_EXPIRED_BOOKING_BY_ROOM -> {

                bookingService.findExpiredByRoom(rooms.get(enterRoomName(in, getRoomNames())));
            }
            case FIND_BOOKING_BY_DAY ->
                    bookingService.findAllBookingByDay(enterDate(in));

            case FIND_ROOM_BY_EQUIPMENTS -> {
                Set<Equipment> setByInput = Equipment.findSetByInput(enterEquipment(in));
                if(setByInput.isEmpty()) {
                    throw new InputMismatchException();
                }
                roomService.findByEquipments(setByInput);
            }
            case FIND_ROOM_BY_MIN_CAPACITY -> {
                roomService.findByMinCapacity(enterCapacity(in));
            }
            default -> System.out.printf("Нет реализации для действия %s цифра %s \n", action, action.getCode());

        }


    }


    private Optional<U> auth(String login, String password) {
        System.out.println("Попытка авторизоваться");
        Optional<U> auth = userService.auth(login, password);

        setAuth(auth.isPresent());

        if(isAuthorized(false)) System.out.println("Авторизация успешна");
        else System.out.println("Неверные данные");
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
    @NotNull
    private Set<String> getRoomNames() {
        return this.rooms.keySet();
    }
}