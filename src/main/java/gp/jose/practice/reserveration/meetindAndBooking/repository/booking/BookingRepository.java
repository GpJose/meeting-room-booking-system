package gp.jose.practice.reserveration.meetindAndBooking.repository.booking;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

import java.util.*;
import java.util.stream.Collectors;

public class BookingRepository implements BookingRepositoryInterface {

    private final Map<String, TreeSet<BookingInterface>> upcomingMeetings = new HashMap<>();
    private final Map<String, TreeSet<BookingInterface>> completedMeetings = new HashMap<>();

    public void save(BookingInterface booking) {
        upcomingMeetings.computeIfAbsent(booking.getReserverRoomName(), k -> new TreeSet<>(
                Comparator.comparing(BookingInterface::getStartDateTime)
        )).add(booking);
    }

    public List<BookingInterface> findAll() {
        return upcomingMeetings.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public TreeSet<BookingInterface> getMeetingsForRoom(RoomInterface room) {
        return upcomingMeetings.getOrDefault(room, new TreeSet<>(
                Comparator.comparing(BookingInterface::getStartDateTime)
        ));
    }
}
