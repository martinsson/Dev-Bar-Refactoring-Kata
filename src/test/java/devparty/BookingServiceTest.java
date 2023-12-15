package devparty;

import devparty.api.BookingController;
import devparty.repository.*;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {


    @Test
    public void reserveBarWhenAtLeast60PercentOfDevsAreAvailable() {
        String indoorBarName = "Bar La Belle Equipe";
        List<BarData> indoorBars = asList(
                barWith(indoorBarName, asList(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
        );
        List<DevData> developers = asList(
                new DevData("Alice", asList(WEDNESDAY, THURSDAY, FRIDAY)),
                new DevData("Bob", asList(THURSDAY)),
                new DevData("Chad", asList(FRIDAY)),
                new DevData("Dan", asList(WEDNESDAY, THURSDAY)),
                new DevData("Eve", asList(THURSDAY))
        );

        BookingController controller = buildController(indoorBars, developers);
        var bookSuccess = controller.makeBooking();
        assertTrue(bookSuccess);
        BookingData result = controller.get().stream().findFirst().orElse(null);

        assertNotNull(result);
        assertEquals(THURSDAY, result.getDate());
        assertEquals(indoorBarName, result.getBar().getName());
    }

    @Test
    public void doNotReserveBarWhenOnly50PercentOfDevsAreAvailable() {
        String indoorBarName = "Bar La Belle Equipe";
        List<BarData> indoorBars = asList(
                barWith(indoorBarName, asList(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
        );

        List<DevData> developers = asList(
                new DevData("Alice", asList(WEDNESDAY, FRIDAY)),
                new DevData("Bob", asList(THURSDAY)),
                new DevData("Chad", asList(FRIDAY)),
                new DevData("Dan", asList(WEDNESDAY)),
                new DevData("Eve", asList(THURSDAY))
        );

        BookingController controller = buildController(indoorBars, developers);
        var bookSuccess = controller.makeBooking();
        assertFalse(bookSuccess);
    }

    @Test
    public void reserveBarOnlyWhenBarIsOpen() {
        String indoorBarName = "Bar La Belle Equipe";
        List<BarData> indoorBars = asList(
                barWith("another name", asList( DayOfWeek.FRIDAY)),
                barWith(indoorBarName, asList( DayOfWeek.THURSDAY))
        );
        List<DevData> developers = asList(
                new DevData("Bob", asList(THURSDAY)),
                new DevData("Eve", asList(THURSDAY))
        );

        BookingController controller = buildController(indoorBars, developers);
        var bookSuccess = controller.makeBooking();
        assertTrue(bookSuccess);
        BookingData result = controller.get().stream().findFirst().orElse(null);

        assertNotNull(result);
        assertEquals(THURSDAY, result.getDate());
        assertEquals(indoorBarName, result.getBar().getName());
    }

    @Test
    public void doNotReserveWhenBarsAreClosed() {
        String indoorBarName = "Bar La Belle Equipe";
        List<BarData> indoorBars = asList(
                barWith("another name", asList( DayOfWeek.FRIDAY)),
                barWith(indoorBarName, asList( DayOfWeek.THURSDAY))
        );
        List<DevData> developers = asList(
                new DevData("Bob", asList(WEDNESDAY)),
                new DevData("Eve", asList(WEDNESDAY))
        );

        BookingController controller = buildController(indoorBars, developers);
        var bookSuccess = controller.makeBooking();

        assertFalse(bookSuccess);

    }

    @Test
    void chooseABarWithEnoughSpace() {
        String indoorBarName = "Bar La Belle Equipe";
        List<BarData> indoorBars = asList(
                barWith(indoorBarName, 3, asList( DayOfWeek.THURSDAY))
        );
        List<DevData> developers = asList(
                new DevData("Bob", asList(THURSDAY)),
                new DevData("Eve", asList(THURSDAY)),
                new DevData("Fred", asList(THURSDAY)),
                new DevData("Marie", asList(THURSDAY))

        );

        BookingController controller = buildController(indoorBars, developers);
        var bookSuccess = controller.makeBooking();

        assertFalse(bookSuccess);

    }

    @Test
    void preferBoats() {
        String indoorBarName = "Bar La Belle Equipe";
        List<BarData> indoorBars = asList(
                barWith(indoorBarName, 3, asList( DayOfWeek.THURSDAY))
        );
        List<BoatData> boats = asList(
                new BoatData(indoorBarName, 3)
        );
        List<DevData> developers = asList(
                new DevData("Bob", asList(THURSDAY)),
                new DevData("Eve", asList(THURSDAY))
        );

        BookingController controller = buildController(indoorBars, developers, boats);
        var bookSuccess = controller.makeBooking();

        assertTrue(bookSuccess);

        BookingData result = controller.get().stream().findFirst().orElse(null);

        assertNotNull(result);
        assertEquals(THURSDAY, result.getDate());
        assertEquals(indoorBarName, result.getBar().getName());

    }

    private static BookingController buildController(List<BarData> barData,
                                                     List<DevData> devData) {
        List<BoatData> boats = Collections.emptyList();
        return buildController(barData, devData, boats);
    }

    private static BookingController buildController(List<BarData> barData, List<DevData> devData, List<BoatData> boats) {
        var boatRepo = new FakeBoatRepository(boats);
        var bookingRepository = new FakeBookingRepository();
        return new BookingController(new BookingService(
                new FakeBarRepository(barData),
                new FakeDevRepository(devData),
                boatRepo,
                bookingRepository),
                bookingRepository);
    }


    private BarData barWith(String indoorBarName, List<DayOfWeek> list) {
        return new BarData(indoorBarName, 10, list);
    }

    private BarData barWith(String indoorBarName, int capacity, List<DayOfWeek> list) {
        return new BarData(indoorBarName, capacity, list);
    }

    private static final LocalDate WEDNESDAY = LocalDate.of(2022, 5, 11);
    private static final LocalDate THURSDAY = WEDNESDAY.plusDays(1);
    private static final LocalDate FRIDAY = WEDNESDAY.plusDays(2);

}