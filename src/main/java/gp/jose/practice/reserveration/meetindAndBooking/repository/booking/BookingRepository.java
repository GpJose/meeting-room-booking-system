package gp.jose.practice.reserveration.meetindAndBooking.repository.booking;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class BookingRepository implements BookingRepositoryInterface {

    private final AtomicLong nextVal = new AtomicLong(0);

    private final Map<String, TreeSet<BookingInterface>> upcomingMeetings = new HashMap<>();
    private final Map<String, TreeSet<BookingInterface>> completedMeetings = new HashMap<>();

    @Override
    public TreeSet<BookingInterface> findAllBookingByDay(LocalDate date) {
        if (date == null) {
            return getBookingInterfaces();
        }

        return upcomingMeetings.values().stream()
                .flatMap(TreeSet::stream)
                .filter(booking -> booking.getStartDateTime().toLocalDate().equals(date))
                .collect(Collectors.toCollection(this::getBookingInterfaces));
    }

    private TreeSet<BookingInterface> getBookingInterfaces() {
        return getComparableTreeSet();
    }

    @NotNull
    private static TreeSet<BookingInterface> getComparableTreeSet() {
        return new TreeSet<>(
                Comparator.comparing(BookingInterface::getReserveId)
        );
    }

    @Override
    public TreeSet<BookingInterface> findAllUpcomingByRoom(RoomInterface room) {
        return upcomingMeetings.getOrDefault(room.roomName(), new TreeSet<>(getBookingInterfaces()));
    }

    public BookingInterface save(BookingInterface booking) {
        upcomingMeetings.computeIfAbsent(booking.getReserverRoomName(), k -> getBookingInterfaces()).add(booking);
       return booking;
    }

    @Override
    public Long nextVal() {
        return nextVal.incrementAndGet();
    }

    @Override
    public TreeSet<BookingInterface> findAllExpiredByRoom(RoomInterface room) {
        return completedMeetings.getOrDefault(room.roomName(), getComparableTreeSet());
    }

    @Override
    public boolean setStatus(RoomInterface roomInterface, Long id, BookingStatus status) {
        TreeSet<BookingInterface> upcoming = findAllUpcomingByRoom(roomInterface);
        if (upcoming == null) {
            return false;
        }

        return upcoming.stream()
                .filter(bookingInterface -> bookingInterface.getReserveId().equals(id))
                .findFirst()
                .map(booking -> {
                    upcoming.remove(booking);

                    booking.setStatus(status);

                    if (status == BookingStatus.COMPLETED || status == BookingStatus.CANCELLED) {
                        completedMeetings.computeIfAbsent(roomInterface.roomName(), k -> getBookingInterfaces()).add(booking);
                    } else {
                        upcoming.add(booking);
                    }

                    return true;
                })
                .orElse(false);
    }


}
