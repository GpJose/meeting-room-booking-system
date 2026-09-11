package gp.jose.practice.reserveration.meetindAndBooking;

import gp.jose.practice.reserveration.meetindAndBooking.factory.impl.DefaultRoomFactoryImpl;
import gp.jose.practice.reserveration.meetindAndBooking.factory.impl.DefaultUserFactoryImpl;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.ActionsEnum;
import gp.jose.practice.reserveration.meetindAndBooking.service.impl.ControlService;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {

        DefaultUserFactoryImpl userFactory = new DefaultUserFactoryImpl();
        DefaultRoomFactoryImpl roomFactory = new DefaultRoomFactoryImpl();

        actions(new ControlService<>(userFactory, roomFactory));

    }

    private static void actions(
            final ControlService<? extends UserInterface, ? extends RoomInterface> controlService) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {

                controlService.printMenu();

                String inputLine = scanner.nextLine().trim();

                if (ActionsEnum.CLOSE_APP.getCode().toString().equals(inputLine)) {
                    System.out.println("App closing...");
                    break;
                }

                ActionsEnum.getEnum(Integer.parseInt(inputLine))
                        .ifPresentOrElse(actionsEnum -> controlService.action(actionsEnum, scanner),
                                () -> System.out.println("В меню нет этого пункта"));

            } catch (NumberFormatException e) {
                System.out.println("Ввдеите корректное число");
            }
        }
    }
}
