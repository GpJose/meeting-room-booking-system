package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.BookingInterface;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import gp.jose.practice.reserveration.meetindAndBooking.repository.booking.BookingRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookingServiceImplTest {

    private BookingServiceImpl bookingService;
    private RoomInterface mockRoom;
    private BookingInterface mockBooking;

    private static final LocalDateTime BOOKING_START = LocalDateTime.of(2026, 9, 15, 13, 0);
    private static final LocalDateTime BOOKING_END = LocalDateTime.of(2026, 9, 15, 14, 0);

    @BeforeEach
    void setUp() {
        BookingRepositoryInterface mockRepository = Mockito.mock(BookingRepositoryInterface.class);
        bookingService = new BookingServiceImpl(mockRepository);
        mockRoom = Mockito.mock(RoomInterface.class);

        mockBooking = Mockito.mock(BookingInterface.class);
        Mockito.when(mockBooking.getStartDateTime()).thenReturn(BOOKING_START);
        Mockito.when(mockBooking.getEndDateTime()).thenReturn(BOOKING_END);
    }

    @ParameterizedTest(name = "{index} => Слот {1} - {2} должен быть свободен? Ответ: {3} ({0})")
    @MethodSource("provideTimeSlots")
    @DisplayName("Проверка пересечения интервалов")
    void testIsFreeOverlappingScenarios(LocalDateTime testStart, LocalDateTime testEnd, boolean expectedIsFree) {
        TreeSet<BookingInterface> roomBookings = new TreeSet<>((b1, b2) -> b1.getStartDateTime().compareTo(b2.getStartDateTime()));
        roomBookings.add(mockBooking);

        boolean actualIsFree = bookingService.isFree(mockRoom, roomBookings, testStart, testEnd);

        assertEquals(expectedIsFree, actualIsFree, () -> "Ошибка в сценарии");
    }

    private static Stream<Arguments> provideTimeSlots() {
        return Stream.of(
                Arguments.of(BOOKING_START.minusHours(2), BOOKING_START.minusHours(1), true),
                Arguments.of(BOOKING_START.minusHours(1), BOOKING_START, true),
                Arguments.of(BOOKING_START.minusMinutes(30), BOOKING_START.plusMinutes(30), false),
                Arguments.of(BOOKING_START.plusMinutes(15), BOOKING_END.minusMinutes(15), false),
                Arguments.of(BOOKING_START, BOOKING_END, false),
                Arguments.of(BOOKING_START.plusMinutes(30), BOOKING_END.plusMinutes(30), false),
                Arguments.of(BOOKING_END, BOOKING_END.plusHours(1), true),
                Arguments.of(BOOKING_END.plusHours(1), BOOKING_END.plusHours(2), true),
                Arguments.of(BOOKING_START.minusMinutes(30), BOOKING_END.plusMinutes(30), false)
        );
    }
}