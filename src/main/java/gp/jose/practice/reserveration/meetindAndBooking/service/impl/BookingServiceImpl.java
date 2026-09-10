package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.impl.Booking;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.BookingServiceInterface;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

@Getter
public class BookingServiceImpl <R extends  RoomInterface> implements BookingServiceInterface<R> {

    private final HashMap<R, TreeSet<Booking>> upcomingMeetings = new HashMap<>();
    private final HashMap<R, ArrayDeque<Booking>> completedMeetings = new HashMap<>();

    public boolean createMeeting(R room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {

        if (isFree(room, toDate, starTime, endTime)) {

            return true;
        }
        return false;
    }

    @Override
    public Long reserve(UserInterface user, RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {
        return 0L;
    }

    @Override
    public boolean canselReserve(UserInterface userInterface, RoomInterface room) {
        return false;
    }

    @Override
    public boolean isFree(R room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {

        TreeSet<Booking> bookings = upcomingMeetings.get(room);

        return bookings
                .stream()
                .noneMatch(booking -> isOverlapping(starTime, endTime, booking));

    }

    private boolean isOverlapping(LocalTime startTime, LocalTime endTime, Booking booking) {
        return startTime.isBefore(booking.getEndTime()) || endTime.isAfter(booking.getStartTime());
    }

    private void addMeeting(R room, LocalDate toDate, LocalTime starTime, LocalTime endTime, Long userId) {

        upcomingMeetings.computeIfAbsent(room, k -> new TreeSet<>(
                Comparator.comparing(Booking::getStartTime)
        )).add(Booking
                .builder()
                .build());

    }
}
