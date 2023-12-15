package devparty.repository;

import java.time.DayOfWeek;
import java.util.List;

public class BarData {
    private final String name;
    private final int capacity;
    private final List<DayOfWeek> open;

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<DayOfWeek> getOpen() {
        return open;
    }

    public BarData(String name, int capacity, List<DayOfWeek> openDays) {
        this.name = name;
        this.capacity = capacity;
        this.open = openDays;
    }
}
