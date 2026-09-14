package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;
import gp.jose.practice.reserveration.meetindAndBooking.repository.room.RoomRepositoryInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.RoomServiceInterface;

import java.util.*;
import java.util.stream.Collectors;


public class RoomServiceImpl <R extends RoomInterface> implements RoomServiceInterface<R> {

    private final RoomRepositoryInterface<R> roomRepositoryInterface;

    public RoomServiceImpl(RoomRepositoryInterface<R> roomRepositoryInterface) {
        this.roomRepositoryInterface = roomRepositoryInterface;
        Objects.requireNonNull(this.roomRepositoryInterface);
    }

    @Override
    public Map<String, R> findAll() {
        return roomRepositoryInterface.findAll();
    }

    @Override
    public void findByMinCapacity(Integer min) {
        List<R> byMinCapacity = roomRepositoryInterface.findByMinCapacity(min);
        if(byMinCapacity.isEmpty()) System.out.printf("Комнаты с вместимостью %s не найдены\n", min);
        else System.out.printf("Найденые комнаты : %s\n", byMinCapacity);
    }

    @Override
    public void findByEquipments(Set<Equipment> equipments) {

        if(equipments.isEmpty())
            System.out.printf("Комнаты по оборудованиям %s найдены : %s \n",
                    equipments,
                    findAll().toString());

        Set<R> collect = findAll()
                .values()
                .stream()
                .filter(r -> r.equipments().containsAll(equipments))
                .collect(Collectors.toSet());

        if(! collect.isEmpty()) {

            System.out.printf("Комнаты по оборудованиям %s найдены : %s \n",
                    equipments,
                    collect);
            return;
        }
        System.out.printf("Комнаты по оборудованиям %s не найдены\n", equipments);
    }



}
