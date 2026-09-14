package gp.jose.practice.reserveration.meetindAndBooking.utils;


import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputUtil {

    private static final Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String enterFio(Scanner in) {
        System.out.print("Введите ФИО: ");
        return in.nextLine().trim();
    }

    public static String enterPassword(Scanner in) {
        System.out.print("Введите пароль: ");
        return in.nextLine();
    }

    public static String enterLogin(Scanner in) {
        System.out.print("Введите логин: ");
        return in.nextLine();
    }

    public static LocalDateTime enterLocalDate(Scanner in, boolean isStart) {
        if(isStart) {
            System.out.println("Введите дату начала в формате : ГГГГ-ММ-ДД ЧЧ:ММ");
        } else System.out.println("Введите дату окончания в формате : ГГГГ-ММ-ДД ЧЧ:ММ");

        String s = in.nextLine();
        if( ! s.matches(datePattern.pattern()) ) {
            throw new InputMismatchException("Не правильный формат даты. Введите дату в фомрате ГГГГ-ММ-ДД ЧЧ:ММ"); }

        return LocalDateTime.parse(s, formatter);

    }

    private static void printRoomName(Set<String> roomNames) {
        StringBuilder stringBuilder = new StringBuilder("Список комнат : \n");
        roomNames.forEach(s -> stringBuilder.append("* ").append(s).append("\n"));
        System.out.println(stringBuilder);
    }

    public static String enterRoomName(Scanner in, Set<String> roomName) {
        printRoomName(roomName);
        System.out.println("Введите имя комнаты : ");
        String pattern = roomName.stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("|", "^(", ")$"));
        String next = in.nextLine();
        if( ! next.matches( pattern)) {
            throw new InputMismatchException("Не правильнаая комната ");
        }
        return next;
    }
    public static LocalDate enterDate(Scanner in) {
        System.out.println("Введите дату в формате : ГГГГ-ММ-ДД");
        String s = in.nextLine().trim();
        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new InputMismatchException("Неправильный формат даты. Введите дату в формате ГГГГ-ММ-ДД");
        }
    }

    public static Long enterBookingId(Scanner in) {
        System.out.println("Введите Id встречи ");
        String s = getDigitOnlyStr(in);
        return Long.parseLong(s);
    }
    public static Integer enterCapacity(Scanner in) {
        System.out.println("Введите минимум вместимость");
        return Integer.parseInt(getDigitOnlyStr(in));
    }

    private static String getDigitOnlyStr(Scanner in) {
        String s = in.nextLine().trim();
        if(! s.matches("^\\d+$")) {
            throw new InputMismatchException("ID состоит только из цифр");
        }
        return s;
    }

    private static void printEquipment() {
        System.out.println(Arrays.toString(Equipment.values()));
    }
    public static String enterEquipment(Scanner in) {
        printEquipment();
        System.out.println("Введите Equipment через запятую");
        return in.nextLine();
    }
}
