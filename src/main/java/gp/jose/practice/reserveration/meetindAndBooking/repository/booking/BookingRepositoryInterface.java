package gp.jose.practice.reserveration.meetindAndBooking.repository.booking;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;

import java.time.LocalDate;
import java.util.TreeSet;

public interface BookingRepositoryInterface {
    TreeSet<BookingInterface> findAllBookingByDay(LocalDate date);
    TreeSet<BookingInterface> findAllUpcomingByRoom(RoomInterface room);
    BookingInterface save(BookingInterface booking);
    Long nextVal();
    TreeSet<BookingInterface> findAllExpiredByRoom(RoomInterface room);
    boolean setStatus(RoomInterface roomInterface, Long id, BookingStatus status);
}
