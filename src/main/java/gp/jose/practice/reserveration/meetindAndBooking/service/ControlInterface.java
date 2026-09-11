package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.util.Scanner;

public interface ControlInterface
//        <U extends UserInterface, R extends RoomInterface>
//        extends UserServiceInterface<U>, RoomInterface
        {
            public void action(Integer action, Scanner in);
}
