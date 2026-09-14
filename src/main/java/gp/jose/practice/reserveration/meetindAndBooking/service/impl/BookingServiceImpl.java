package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.enums.BookingStatus;
import gp.jose.practice.reserveration.meetindAndBooking.model.impl.Booking;
import gp.jose.practice.reserveration.meetindAndBooking.repository.booking.BookingRepositoryInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.BookingServiceInterface;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
public class BookingServiceImpl implements BookingServiceInterface {

    private final BookingRepositoryInterface bookingRepository;


    public BookingServiceImpl(BookingRepositoryInterface bookingRepository) {

        Objects.requireNonNull(bookingRepository,
                "BookingRepositoryInterface cannot be null in BookingServiceImpl construct");

        this.bookingRepository = bookingRepository;
    }

    @Override
    public boolean createMeeting(RoomInterface room, UserInterface user, LocalDateTime startTime, LocalDateTime endTime) {

        TreeSet<BookingInterface> bookingByRoom = findBookingByRoom(room, false);

        if (bookingByRoom == null || bookingByRoom.isEmpty() || isFree(room, bookingByRoom, startTime, endTime)) {
            getSave(room, user, startTime, endTime);
            return true;
        }


        System.out.printf("Встреча не создана, комната %s занята.\n", room.roomName());
        return false;
    }

    private BookingInterface getSave(RoomInterface room, UserInterface user, LocalDateTime starTime, LocalDateTime endTime) {
        BookingInterface save = bookingRepository.save(getBuild(room, user, starTime, endTime));

        System.out.printf("Встреча создана комната %s старт %s конец %s.\n", room.roomName(), starTime, endTime);
        return save;
    }

    private Booking getBuild(RoomInterface room, UserInterface user, LocalDateTime starTime, LocalDateTime endTime) {
        return Booking
                .builder()
                .reserveId(bookingRepository.nextVal())
                .user(user)
                .room(room)
                .status(BookingStatus.CREATED)
                .startDateTime(starTime)
                .endDateTime(endTime)
                .build();
    }

    private TreeSet<BookingInterface> findBookingByName(RoomInterface roomName) {
        return bookingRepository.findAllUpcomingByRoom(roomName);
    }

    @Override
    public boolean cancelReserve(UserInterface userInterface, RoomInterface room, Long reservedId) {

        Optional<BookingInterface> any = findBookingByName(room)
                .stream()
                .filter(booking -> Objects.equals(booking.getReserveId(), reservedId))
                .filter(booking -> booking.reservedBy().equals(userInterface.getLogin())).findAny();

        if(any.isPresent()) {
            boolean b = bookingRepository.setStatus(room, reservedId, BookingStatus.CANCELLED);
            if(b) System.out.println("Статус обновлен");
            else System.out.println("Статус не обновлен");
            return b;
        }
        return  false;
    }

    @Override
    public TreeSet<BookingInterface> findBookingByRoom(RoomInterface room, boolean sout) {
        TreeSet<BookingInterface> allByName = bookingRepository.findAllUpcomingByRoom(room);
        if(allByName != null) {
            if(allByName.isEmpty()) System.out.printf("Для комнаты %s нет запланированых встреч\n", room);
            else if(sout) System.out.printf("Запланированые встречи комнаты %s \n%s "
                    , room
                    , allByName.stream()
                            .map(booking -> " * " + booking.toString())
                            .collect(Collectors.joining("\n")));
        }

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

    @Override
    public TreeSet<BookingInterface> findExpiredByRoom(RoomInterface room) {

        TreeSet<BookingInterface> allExpiredByRoom = bookingRepository.findAllExpiredByRoom(room);
        if(allExpiredByRoom.isEmpty()) System.out.printf("Для комнаты %s нет прошлых встреч\n", room.roomName());
        else System.out.printf("Прошлые встречи комнаты %s : %s\n", room.roomName(), allExpiredByRoom);

        return bookingRepository.findAllExpiredByRoom(room);
    }

    @Override
    public TreeSet<BookingInterface> findAllBookingByDay(LocalDate date) {
        TreeSet<BookingInterface> allBookingByDay = bookingRepository.findAllBookingByDay(date);
        if(allBookingByDay.isEmpty()) System.out.println("Встреч на день нет");
        else System.out.printf("Встречи на день : %s \n", allBookingByDay);
        return allBookingByDay;
    }

    private boolean isOverlapping(LocalDateTime startTime, LocalDateTime endTime, BookingInterface booking) {
        return startTime.isBefore(booking.getEndDateTime()) && endTime.isAfter(booking.getStartDateTime());
    }

}
