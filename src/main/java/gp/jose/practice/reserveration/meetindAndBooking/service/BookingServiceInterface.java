package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeSet;

public interface BookingServiceInterface {

    boolean cancelReserve(UserInterface userInterface,
                          RoomInterface room,
                          Long reservedId);

    boolean createMeeting(RoomInterface roomName, UserInterface user,
                          LocalDateTime starTime,
                          LocalDateTime endTime);


    TreeSet<BookingInterface> findBookingByRoom(RoomInterface room, boolean sout);

    boolean isFree(RoomInterface roomName,
                    TreeSet<BookingInterface> roomBookings,
                    LocalDateTime starTime,
                    LocalDateTime endTime);

    TreeSet<BookingInterface> findExpiredByRoom(RoomInterface room);


    TreeSet<BookingInterface> findAllBookingByDay(LocalDate date);
}
