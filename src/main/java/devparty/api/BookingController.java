package devparty.api;

import devparty.model.BookingData;
import devparty.BookingService;
import devparty.IBookingRepository;

import java.util.List;

public class BookingController {
    private final BookingService bookingService;
    private final IBookingRepository bookingRepository;

    public BookingController(BookingService bookingService, IBookingRepository bookingRepository) {

        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    public boolean makeBooking() {
        return bookingService.reserveBar();

    }

    public List<BookingData> get() {
        return bookingRepository.getUpcomingBookings();
    }
}
