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
                // Assuming Bar constructor takes name and list of open days
        );
        List<DevData> devData = asList(
                new DevData("Alice", asList(WEDNESDAY, THURSDAY, FRIDAY)),
                new DevData("Bob", asList(THURSDAY)),
                new DevData("Chad", asList(FRIDAY)),
                new DevData("Dan", asList(WEDNESDAY, THURSDAY)),
                new DevData("Eve", asList(THURSDAY))
                // Assuming DevData constructor takes name and list of on-site days
        );

        BookingController controller = buildController(indoorBars, devData);
        var bookSuccess = controller.makeBooking();
        assertTrue(bookSuccess);
        BookingData result = controller.get().stream().findFirst().orElse(null);

        assertNotNull(result);
        assertEquals(THURSDAY, result.getDate());
        assertEquals(indoorBarName, result.getBar().getName());
    }

    private static BookingController buildController(List<BarData> barData,
                                                     List<DevData> devData) {
        var bookingRepository = new FakeBookingRepository();
        return new BookingController(new BookingService(
                new FakeBarRepository(barData),
                new FakeDevRepository(devData),
                new FakeBoatRepository(Collections.emptyList()),
                bookingRepository),
                bookingRepository);
    }


    private BarData barWith(String indoorBarName, List<DayOfWeek> list) {
        return new BarData(indoorBarName, 10, list);
    }


    private static final LocalDate WEDNESDAY = LocalDate.of(2022, 5, 11);
    private static final LocalDate THURSDAY = WEDNESDAY.plusDays(1);
    private static final LocalDate FRIDAY = WEDNESDAY.plusDays(2);

}