package gp.jose.practice.reserveration.meetindAndBooking.utils;


import java.util.Scanner;

public class InputUtil {

    public static String enterFio(Scanner in) {
        System.out.print("Введите ФИО: ");
        return in.nextLine().trim();
    }

    public static String enterPassword(Scanner in) {
        System.out.print("Введите пароль: ");
        return in.nextLine().trim();
    }

    public static String enterLogin(Scanner in) {
        System.out.print("Введите логин: ");
        return in.nextLine().trim();
    }

    public static String enterRoomName(Scanner in) {
        System.out.print("Введите название комнаты: ");
        return in.nextLine().trim();
    }
}
