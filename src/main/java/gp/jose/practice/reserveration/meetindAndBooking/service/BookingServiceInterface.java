package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingServiceInterface {

    boolean canselReserve(UserInterface userInterface, RoomInterface room);
    boolean createMeeting(RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime);
    void findBookingByDate();
    void findBookingByRoom();
}
