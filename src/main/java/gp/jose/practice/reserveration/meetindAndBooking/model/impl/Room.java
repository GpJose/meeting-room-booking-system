package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;

import java.util.Set;

//  название, вместимость, список оборудования (проектор, экран и т.д.).
public record Room  (
        String roomName,
        Integer capacity,
        Set<Equipment> equipments
)
        implements RoomInterface {

}
