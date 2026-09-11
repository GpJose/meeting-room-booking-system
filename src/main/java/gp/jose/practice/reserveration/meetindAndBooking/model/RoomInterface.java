package gp.jose.practice.reserveration.meetindAndBooking.model;

import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;

import java.util.Set;

public interface RoomInterface {
    String roomName();
    Integer capacity();
    Set<Equipment> equipments();
}
