package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;
import gp.jose.practice.reserveration.meetindAndBooking.repository.room.RoomRepositoryInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.RoomServiceInterface;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class RoomServiceImpl <R extends RoomInterface> implements RoomServiceInterface<R> {

    private final RoomRepositoryInterface<R> roomRepositoryInterface;

    public RoomServiceImpl(RoomRepositoryInterface<R> roomRepositoryInterface) {
        this.roomRepositoryInterface = roomRepositoryInterface;
        Objects.requireNonNull(this.roomRepositoryInterface);
    }

    @Override
    public TreeSet<R> findAll() {
        return roomRepositoryInterface.findAll();
    }

    @Override
    public void findByRoomName(String name) {
        System.out.println("Поиск комнаты : " + name);
        Optional<R> byName = roomRepositoryInterface.findByName(name);
        if(byName.isPresent()) System.out.printf("Комната %s найдена : %s\n", name, byName.get());
        else System.out.printf("Комната %s не найдена\n", name);
    }

    @Override
    public void findByCapacityRange(Integer min, Integer max) {

        return ;
    }

    @Override
    public void findByEquipments(Set<Equipment> equipments) {

        if(equipments.isEmpty())
            System.out.printf("Комнаты по оборудованиям %s найдены : %s \n",
                    equipments,
                    findAll().toString());

        Set<R> collect = findAll()
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
