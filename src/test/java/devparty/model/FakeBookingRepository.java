package devparty.model;

import devparty.IBookingRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeBookingRepository  implements IBookingRepository {
    private final List<BookingData> bookingData = new ArrayList<>();
    @Override
    public void save(BookingData bookingData) {
        this.bookingData.add(bookingData);
    }

    @Override
    public List<BookingData> getUpcomingBookings() {
        return bookingData;
    }

    public BookingData getUpcomingBooking(LocalDate date) {
        return bookingData.stream().filter(d -> d.getDate().isEqual(date) ).findFirst().orElse(null);
    }
}
