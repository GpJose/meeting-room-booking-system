package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

// комната, кто бронирует, дата, время начала и окончания.
@Getter
@Builder
public class Booking extends TreeSet<Booking> implements BookingInterface {

    private Long reserveId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private RoomInterface room;
    private UserInterface user;
    private BookingStatus status;

    @Override
    public String reservedBy() {
        return user.FIO();
    }

    @Override
    public String reservedRoom() {
        return room.roomName();
    }
}
