package gp.jose.practice.reserveration.meetindAndBooking.repository.room;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

import java.util.HashMap;
import java.util.Optional;
import java.util.TreeSet;

public interface RoomRepositoryInterface<R extends RoomInterface>  {

    HashMap<String, R> findAll();
    Optional<R> findByName(String id);

}
