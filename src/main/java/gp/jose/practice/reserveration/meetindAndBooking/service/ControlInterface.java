package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

public interface ControlInterface <U extends UserInterface, R extends RoomInterface>
        extends UserServiceInterface<U>, BookingServiceInterface<R>, RoomInterface{
}
