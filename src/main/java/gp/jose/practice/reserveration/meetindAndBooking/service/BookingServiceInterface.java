package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

public interface BookingServiceInterface {

    boolean canselReserve(UserInterface userInterface, RoomInterface room);
    boolean createMeeting(RoomInterface roomName, UserInterface user, LocalDateTime starTime, LocalDateTime endTime);
    Optional<BookingInterface> findBookingByDate(RoomInterface room, LocalDateTime startDateTime, LocalDateTime endDateTime);
    TreeSet<BookingInterface> findBookingByRoom(RoomInterface room);

    boolean isFree(RoomInterface roomName,
                   TreeSet<BookingInterface> roomBookings,
                   LocalDateTime starTime,
                   LocalDateTime endTime);
}
