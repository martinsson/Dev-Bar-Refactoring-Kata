package devparty;

import devparty.repository.BarData;

import java.util.List;

public interface IBarRepository {
    List<BarData> get();
}
