package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingServiceInterface <R extends  RoomInterface> {
    // TODO лист зарезервированных
    Long reserve(UserInterface user, RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime);
    boolean canselReserve(UserInterface userInterface, RoomInterface room);
    boolean isFree(R room, LocalDate toDate, LocalTime starTime, LocalTime endTime);
    boolean createMeeting(R room, LocalDate toDate, LocalTime starTime, LocalTime endTime);
}
