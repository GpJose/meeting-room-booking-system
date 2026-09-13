package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.impl.Booking;
import gp.jose.practice.reserveration.meetindAndBooking.repository.booking.BookingRepositoryInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.BookingServiceInterface;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

@Getter
public class BookingServiceImpl implements BookingServiceInterface {

    private final BookingRepositoryInterface bookingRepository;

    public BookingServiceImpl(BookingRepositoryInterface bookingRepository) {

        Objects.requireNonNull(bookingRepository);

        this.bookingRepository = bookingRepository;
    }

    public boolean createMeeting(RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {

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
    public boolean createMeeting(RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {
        return false;
    }

    @Override
    public void findBookingByDate() {

    }

    @Override
    public void findBookingByRoom() {

    }

    @Override
    public boolean isFree(RoomInterface room, LocalDate toDate, LocalTime starTime, LocalTime endTime) {

        TreeSet<Booking> bookings = upcomingMeetings.get(room);

        return bookings
                .stream()
                .noneMatch(booking -> isOverlapping(starTime, endTime, booking));

    }

    private boolean isOverlapping(LocalTime startTime, LocalTime endTime, Booking booking) {
        return startTime.isBefore(booking.getEndTime()) || endTime.isAfter(booking.getStartTime());
    }

    @Override
    private void createMeeting(R room, LocalDate toDate, LocalTime starTime, LocalTime endTime, Long userId) {

        upcomingMeetings.computeIfAbsent(room, k -> new TreeSet<>(
                Comparator.comparing(Booking::getStartDateTime)
        )).add(Booking
                .builder()
                .build());

    }
}
