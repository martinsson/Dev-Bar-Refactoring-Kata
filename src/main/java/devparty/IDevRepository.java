package devparty;

import devparty.model.DevData;

import java.util.List;

public interface  IDevRepository {
    public List<DevData> get();
}
