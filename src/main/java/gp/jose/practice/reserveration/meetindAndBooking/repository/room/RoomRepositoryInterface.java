package gp.jose.practice.reserveration.meetindAndBooking.repository.room;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

import java.util.Optional;
import java.util.TreeSet;

public sealed interface RoomRepositoryInterface<R extends RoomInterface> permits RoomsRepository {

    TreeSet<R> findAll();
    Optional<R> findByName(String id);

}
