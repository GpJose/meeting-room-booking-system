package gp.jose.practice.reserveration.meetindAndBooking.repository.booking;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BookingRepository implements BookingRepositoryInterface {

    private final AtomicLong nextVal = new AtomicLong(0);

    private final Map<String, TreeSet<BookingInterface>> upcomingMeetings = new HashMap<>();
    private final Map<String, TreeSet<BookingInterface>> completedMeetings = new HashMap<>();

    @Override
    public TreeSet<BookingInterface> findAllUpcomingByRoom(RoomInterface room) {
        return null;
    }

    public void save(BookingInterface booking) {
            upcomingMeetings.computeIfAbsent(booking.getReserverRoomName(), k -> new TreeSet<>(
                    Comparator.comparing(BookingInterface::getStartDateTime)
            )).add(booking);


    }

    @Override
    public Long nextVal() {
        return nextVal.incrementAndGet();
    }

}
