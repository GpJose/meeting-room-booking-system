package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

// комната, кто бронирует, дата, время начала и окончания.
@Getter
@Builder
public class Booking extends TreeSet<Booking> implements BookingInterface {

    protected Long reserveId;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;
    protected RoomInterface room;
    protected UserInterface user;
    protected BookingStatus status;

    @Override
    public String reservedBy() {
        return user.getFIO();
    }

    @Override
    public String reservedRoom() {
        return room.getRoomName();
    }
}
