package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface RoomServiceInterface <R extends  RoomInterface> {
    Map<String, R> findAll();
    void findByMinCapacity(Integer min);
    void findByEquipments(Set<Equipment> equipments);
}
