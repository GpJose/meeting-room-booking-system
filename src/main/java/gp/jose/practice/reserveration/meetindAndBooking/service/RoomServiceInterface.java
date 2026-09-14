package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public interface RoomServiceInterface <R extends  RoomInterface> {
    HashMap<String, R> findAll();
    void findByCapacityRange(Integer min, Integer max);
    void findByEquipments(Set<Equipment> equipments);
}
