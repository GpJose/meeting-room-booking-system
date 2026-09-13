package gp.jose.practice.reserveration.meetindAndBooking.repository.booking;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

import java.util.List;

public interface BookingRepositoryInterface {
    List<BookingInterface> findAll();
    void save(BookingInterface booking);
}
