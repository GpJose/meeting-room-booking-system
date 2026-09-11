package gp.jose.practice.reserveration.meetindAndBooking;

import gp.jose.practice.reserveration.meetindAndBooking.factory.UserFactoryImpl;
import gp.jose.practice.reserveration.meetindAndBooking.model.impl.User;
import gp.jose.practice.reserveration.meetindAndBooking.service.impl.ControlService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {

        actions(new ControlService<>(new UserFactoryImpl()));

    }

    private static void actions(final ControlService<User> controlService ) {

        Integer input = 10;
        Scanner scanner = new Scanner(System.in);

        do {

            try {
                controlService.printMenu();
                input = scanner.nextInt();
                controlService.action(input, scanner);
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Ввод некорректен");
            }

        } while (input != 10);
    }
}
