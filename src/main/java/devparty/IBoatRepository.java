package devparty;

import devparty.model.BoatData;

import java.util.List;

public interface IBoatRepository {
    List<BoatData> get();
}
