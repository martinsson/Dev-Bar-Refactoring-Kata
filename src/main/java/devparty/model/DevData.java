package devparty.model;

import java.time.LocalDate;
import java.util.List;

public class DevData {
    public String getName() {
        return name;
    }

    public List<LocalDate> getOnSite() {
        return onSite;
    }

    private String name;
    private List<LocalDate> onSite;

    public DevData(String name, List<LocalDate> onSite) {
        this.name = name;
        this.onSite = onSite;
    }
}
