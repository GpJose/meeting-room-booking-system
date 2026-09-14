package gp.jose.practice.reserveration.meetindAndBooking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum ActionsEnum {
    LOGOUT(0, "Выйти из системы", true),
    AUTH(1, "Авторизоваться", false),
    CREATE_USER(2, "Создать пользователя", false),
    CREATE_BOOKING(3, "Создать встречу", true),
    FIND_BOOKING_BY_ROOM(4, "Найти встречи комнаты", false),
    CANCEL_BOOKING(5, "Отменить встречу", true),
    FIND_ALL_EXPIRED_BOOKING_BY_ROOM(6, "Найти все завершенные встречи по комнате", false),
    FIND_ROOM_BY_EQUIPMENTS(7, "Найти комнаты по оборудованиям",false),
    FIND_ROOM_BY_MIN_CAPACITY(8, "Найти комнаты по вместимости", false),
    FIND_BOOKING_BY_DAY(9, "Найти встерчи на дату", false),
    CLOSE_APP(10, "Закрыть приложение", false);
    private final Integer code;
    private final String description;
    private final boolean isAuthRequired;

    public static Optional<ActionsEnum> getEnum(int code) {
        return Arrays.stream(ActionsEnum.values()).filter(actionsEnum -> actionsEnum.getCode() == code).findFirst();
    }

    @Override
    public String toString() {
        return getCode().toString() + " - " + getDescription() + "\n";
    }

    public static String consoleNavigation(boolean authorized) {
        return Arrays.stream(ActionsEnum.values())
                .sorted(Comparator.comparing(ActionsEnum::getCode))
                .filter(actionsEnum -> {
                    if (actionsEnum.isAuthRequired()) {
                        return authorized;
                    }
                    return true;
                })
                .map(ActionsEnum::toString)
                .collect(Collectors.joining());
    }
}
