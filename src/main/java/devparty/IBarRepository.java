package devparty;

import devparty.model.BarData;

import java.util.List;

public interface IBarRepository {
    List<BarData> get();
}
