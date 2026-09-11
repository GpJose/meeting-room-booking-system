package gp.jose.practice.reserveration.meetindAndBooking.factory.impl;

import gp.jose.practice.reserveration.meetindAndBooking.factory.RoomFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;
import gp.jose.practice.reserveration.meetindAndBooking.model.impl.Room;

import java.util.Set;

public class DefaultRoomFactoryImpl implements RoomFactory<Room> {
    @Override
    public Room create(String name, Integer capacity, Set<Equipment> equipment) {
        return new Room(name, capacity, equipment);
    }
}
