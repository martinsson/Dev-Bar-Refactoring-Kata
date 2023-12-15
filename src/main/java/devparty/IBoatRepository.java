package devparty;

import devparty.repository.BoatData;

import java.util.List;

public interface IBoatRepository {
    List<BoatData> get();
}
