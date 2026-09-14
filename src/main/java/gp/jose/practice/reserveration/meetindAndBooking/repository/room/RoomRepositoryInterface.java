package gp.jose.practice.reserveration.meetindAndBooking.repository.room;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

import java.util.*;

public interface RoomRepositoryInterface<R extends RoomInterface>  {

    Map<String, R> findAll();
    Optional<R> findByName(String id);
    List<R> findByMinCapacity(int minCapacity);
}
