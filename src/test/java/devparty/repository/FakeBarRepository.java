package devparty.repository;

import devparty.IBarRepository;

import java.util.List;

public class FakeBarRepository implements IBarRepository {
    private final List<BarData> barData;

    public FakeBarRepository(List<BarData> barData) {
        this.barData = barData;
    }

    @Override
    public List<BarData> get() {
        return barData;
    }
}
