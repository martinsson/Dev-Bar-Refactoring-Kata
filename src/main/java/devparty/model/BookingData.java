package devparty.model;

import devparty.model.BarData;

import java.time.LocalDate;

public class BookingData {
    private final LocalDate date;
    private final BarData bar;

    public BookingData(BarData bar, LocalDate date) {
        this.date = date;
        this.bar = bar;
    }

    public LocalDate getDate() {
        return date;
    }

    public BarData getBar() {
        return bar;
    }
}
