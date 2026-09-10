package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

public interface RoomServiceInterface <T extends  RoomInterface> {
    T getRoom();
    T findByRoomId(Long id);
    T findByRoomName(String name);
    T findByCapacityRange(Integer min, Integer max);

}
