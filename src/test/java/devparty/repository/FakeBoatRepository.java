package devparty.repository;

import devparty.IBoatRepository;

import java.util.List;

public class FakeBoatRepository implements IBoatRepository {
    private final List<BoatData> boats;

    public FakeBoatRepository(List<BoatData> boats) {
        this.boats = boats;
    }

    @Override
    public List<BoatData> get() {
        return boats;
    }
}
