package gp.jose.practice.reserveration.meetindAndBooking.model;

import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;

import java.time.LocalDateTime;

// комната, кто бронирует, дата, время начала и окончания.
public interface BookingInterface {
    LocalDateTime getStartDateTime();
    LocalDateTime getEndDateTime();
    String reservedBy();
    Long getReserveId();
    String getReserverRoomName();
    BookingStatus getStatus();
    void setStatus(BookingStatus status);
}
