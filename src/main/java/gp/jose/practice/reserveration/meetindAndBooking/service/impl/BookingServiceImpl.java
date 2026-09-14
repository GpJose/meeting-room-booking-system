package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;
import gp.jose.practice.reserveration.meetindAndBooking.model.impl.Booking;
import gp.jose.practice.reserveration.meetindAndBooking.repository.booking.BookingRepositoryInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.BookingServiceInterface;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;

@Getter
public class BookingServiceImpl implements BookingServiceInterface {

    private final BookingRepositoryInterface bookingRepository;


    public BookingServiceImpl(BookingRepositoryInterface bookingRepository) {

        Objects.requireNonNull(bookingRepository,
                "BookingRepositoryInterface cannot be null in BookingServiceImpl construct");

        this.bookingRepository = bookingRepository;
    }

    @Override
    public boolean createMeeting(RoomInterface room, UserInterface user, LocalDateTime starTime, LocalDateTime endTime) {

        TreeSet<BookingInterface> bookingByRoom = findBookingByRoom(room);

        if (bookingByRoom.isEmpty() || isFree(room, bookingByRoom, starTime, endTime)) {

            bookingRepository.save(Booking
                    .builder()
                    .reserveId(bookingRepository.nextVal())
                    .user(user)
                    .room(room)
                    .status(BookingStatus.CREATED)
                    .startDateTime(starTime)
                    .endDateTime(endTime)
                    .build());

            return true;
        }
        return false;
    }
    private TreeSet<BookingInterface> findBookingByName(RoomInterface roomName) {
        return bookingRepository.findAllUpcomingByRoom(roomName);
    }

    @Override
    public boolean canselReserve(UserInterface userInterface, RoomInterface room) {
        return false;
    }


    @Override
    public Optional<BookingInterface> findBookingByDate(RoomInterface room, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        return findBookingByName(room).stream()
                .filter(bookingInterface -> bookingInterface.getStartDateTime().isBefore(endDateTime)
                        && bookingInterface.getEndDateTime().isAfter(startDateTime))
                .findAny();
    }

    @Override
    public TreeSet<BookingInterface> findBookingByRoom(RoomInterface room) {
        TreeSet<BookingInterface> allByName = bookingRepository.findAllUpcomingByRoom(room);
        if(allByName.isEmpty()) System.out.printf("Для комнаты %s нет запланированых встреч\n", room);
//        else System.out.printf("Запланированые встречи комнаты %s \n%s "
//                , room
//                , allByName.stream()
//                        .map(booking -> " * " + booking.toString())
//                        .collect(Collectors.joining("\n")));
        return allByName;
    }

    @Override
    public boolean isFree(RoomInterface roomName,
                          TreeSet<BookingInterface> roomBookings,
                          LocalDateTime starTime,
                          LocalDateTime endTime) {

        boolean isFree = roomBookings
                .stream()
                .noneMatch(booking -> isOverlapping(starTime, endTime, booking));
        if(isFree) System.out.printf("Запись для комнаты %s с %s по %s свободна\n", roomName, starTime, endTime);
        else System.out.printf("Запись для комнаты %s с %s по %s занята\n", roomName, starTime, endTime);
        return isFree;
    }

    private boolean isOverlapping(LocalDateTime startTime, LocalDateTime endTime, BookingInterface booking) {
        return startTime.isBefore(booking.getStartDateTime()) || endTime.isAfter(booking.getEndDateTime());
    }

}
