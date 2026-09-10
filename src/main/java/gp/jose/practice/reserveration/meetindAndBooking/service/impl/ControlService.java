package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.ControlInterface;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ControlService implements ControlInterface {

    private UserInterface user;

    @Override
    public Long getRoomId() {
        return 0L;
    }

    @Override
    public String getRoomName() {
        return "";
    }

    @Override
    public Long reserve(UserInterface user, RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {
        return 0L;
    }

    @Override
    public boolean canselReserve(UserInterface userInterface, RoomInterface room) {
        return false;
    }

    @Override
    public boolean isFree(RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {
        return false;
    }

    @Override
    public boolean createMeeting(RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {
        return false;
    }

    @Override
    public boolean auth(String login, String password) {
        return false;
    }

    @Override
    public boolean create(String login, String password) {
        return false;
    }
}
