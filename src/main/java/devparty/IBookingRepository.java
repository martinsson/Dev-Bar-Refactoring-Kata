package devparty;

import devparty.model.BookingData;

import java.util.List;

public interface IBookingRepository {
    void save(BookingData bookingData);

    List<BookingData> getUpcomingBookings();
}
