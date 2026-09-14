package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Builder
@ToString
public class Booking implements BookingInterface {

    private Long reserveId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private RoomInterface room;
    private UserInterface user;
    private BookingStatus status;

    @Override
    public String reservedBy() {
        return user.getLogin();
    }

    @Override
    public String getReserverRoomName() {
        return room.roomName();
    }

    @Override
    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
