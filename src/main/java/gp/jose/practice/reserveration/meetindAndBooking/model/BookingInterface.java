package gp.jose.practice.reserveration.meetindAndBooking.model;

import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalTime;

// комната, кто бронирует, дата, время начала и окончания.
public interface BookingInterface {
    LocalTime getStartTime();
    LocalTime getEndTime();
    LocalDate getDate();
    String reservedBy();
    Long getReserveId();
    String reservedRoom();
    BookingStatus getStatus();
}
