package gp.jose.practice.reserveration.meetindAndBooking.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
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

}
