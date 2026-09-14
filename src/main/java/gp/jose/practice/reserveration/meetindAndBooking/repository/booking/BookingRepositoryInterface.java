package gp.jose.practice.reserveration.meetindAndBooking.repository.booking;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

import java.util.TreeSet;

public interface BookingRepositoryInterface {
    TreeSet<BookingInterface> findAllUpcomingByRoom(RoomInterface room);
    void save(BookingInterface booking);
    Long nextVal();
}
