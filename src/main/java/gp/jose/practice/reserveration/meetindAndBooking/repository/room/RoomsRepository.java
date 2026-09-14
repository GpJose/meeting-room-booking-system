package gp.jose.practice.reserveration.meetindAndBooking.repository.room;

import gp.jose.practice.reserveration.meetindAndBooking.factory.RoomFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;

import java.util.*;


public class RoomsRepository <R extends RoomInterface> implements RoomRepositoryInterface<R> {

    public RoomsRepository(RoomFactory<R> roomFactory) {
        Objects.requireNonNull(roomFactory);
        saveAll(initSave(roomFactory));
    }

    private final HashMap<String, R> rooms = new HashMap<>();

    @Override
    public Map<String, R> findAll() {
        return new HashMap<>(this.rooms);
    }

    @Override
    public Optional<R> findByName(String id) {
        return Optional.ofNullable(rooms.get(id));
    }


    private void saveAll(Set<R> rs) {
        for (R r : rs) {
            rooms.put(r.roomName(),r);
        }
    }

    private Set<R> initSave(RoomFactory<R> roomFactory) {
        return Set.of(
                roomFactory.create("Huddle Room 1", 2, Set.of(Equipment.WHITEBOARD)),
                roomFactory.create("Blue Meet Room", 4, Set.of(Equipment.TV_SCREEN, Equipment.VIDEO_CONFERENCE)),
                roomFactory.create("London Room", 6, Set.of(Equipment.TV_SCREEN, Equipment.WHITEBOARD, Equipment.PHONE)),
                roomFactory.create("Creative Space", 8, Set.of(Equipment.PROJECTOR, Equipment.SCREEN, Equipment.WHITEBOARD)),
                roomFactory.create("Grand Spectrum", 15, Set.of(Equipment.PROJECTOR, Equipment.SCREEN, Equipment.PHONE)),
                roomFactory.create("Executive Boardroom", 12, Set.of(Equipment.TV_SCREEN, Equipment.VIDEO_CONFERENCE, Equipment.PHONE, Equipment.WHITEBOARD)),
                roomFactory.create("Cyber Room", 5, Set.of(Equipment.TV_SCREEN, Equipment.VIDEO_CONFERENCE)),
                roomFactory.create("Training Center Alpha", 30, Set.of(Equipment.PROJECTOR, Equipment.SCREEN, Equipment.WHITEBOARD, Equipment.PHONE)),
                roomFactory.create("Premium Lounge", 4, Set.of(Equipment.TV_SCREEN, Equipment.PHONE)),
                roomFactory.create("Congress Hall", 50, Set.of(Equipment.PROJECTOR, Equipment.SCREEN, Equipment.VIDEO_CONFERENCE, Equipment.PHONE, Equipment.WHITEBOARD))
        );
    }

    public List<R> findByMinCapacity(int minCapacity) {
        return rooms.values().stream()
                .filter(room -> room.capacity() >= minCapacity)
                .sorted(java.util.Comparator.comparingInt(RoomInterface::capacity))
                .toList();
    }
}
