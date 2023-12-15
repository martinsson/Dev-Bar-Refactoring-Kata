package devparty;

import devparty.repository.DevData;

import java.util.Arrays;
import java.util.List;

public interface  IDevRepository {
    public List<DevData> get();
}
