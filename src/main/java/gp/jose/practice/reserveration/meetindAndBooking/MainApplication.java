package gp.jose.practice.reserveration.meetindAndBooking;

import gp.jose.practice.reserveration.meetindAndBooking.factory.impl.DefaultRoomFactoryImpl;
import gp.jose.practice.reserveration.meetindAndBooking.factory.impl.DefaultUserFactoryImpl;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.ActionsEnum;
import gp.jose.practice.reserveration.meetindAndBooking.repository.booking.BookingRepository;
import gp.jose.practice.reserveration.meetindAndBooking.service.impl.BookingServiceImpl;
import gp.jose.practice.reserveration.meetindAndBooking.service.impl.ControlService;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainApplication {

    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));

        // TODO переделать фабрику
        DefaultUserFactoryImpl userFactory = new DefaultUserFactoryImpl();
        DefaultRoomFactoryImpl roomFactory = new DefaultRoomFactoryImpl();

        actions(new ControlService<>(userFactory, roomFactory, new BookingServiceImpl(new BookingRepository())));

    }

    private static void actions(
            final ControlService<? extends UserInterface, ? extends RoomInterface> controlService) {


        Scanner scanner = new Scanner(System.in);

        Pattern actionPattern = Pattern.compile(Arrays.stream(ActionsEnum.values())
                .map(option -> String.valueOf(option.getCode()))
                .collect(Collectors.joining("|", "^(", ")$")));

        while (true) {

            try {

                controlService.printMenu();

                String trim = scanner.nextLine().trim();
                if(! trim.matches(actionPattern.pattern())) {
                    throw new InputMismatchException();
                }
                int inputLine = Integer.parseInt(trim);
                if (ActionsEnum.CLOSE_APP.getCode().equals(inputLine)) {
                    System.out.println("App closing...");
                    break;
                }
                ActionsEnum.getEnum(inputLine)
                        .ifPresentOrElse(actionsEnum -> controlService.action(actionsEnum, scanner),
                                () -> {throw new InputMismatchException();});

            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Ввдеите корректное число");
            }
        }
    }
}
