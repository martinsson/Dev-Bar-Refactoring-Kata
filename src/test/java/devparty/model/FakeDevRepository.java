package devparty.model;

import devparty.IDevRepository;

import java.util.List;

public class FakeDevRepository implements IDevRepository {
    private final List<DevData> devData;

    public FakeDevRepository(List<DevData> devData) {
        this.devData = devData;
    }

    @Override
    public List<DevData> get() {
        return devData;
    }
}
