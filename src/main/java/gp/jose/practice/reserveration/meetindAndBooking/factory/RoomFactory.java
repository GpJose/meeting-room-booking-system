package gp.jose.practice.reserveration.meetindAndBooking.factory;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;

import java.util.Set;

public interface RoomFactory <R extends RoomInterface> {

    R create(String name, Integer capacity, Set<Equipment> equipment);
}
