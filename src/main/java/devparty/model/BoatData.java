package devparty.model;

public class BoatData {

    private final String name;

    public String getName() {
        return name;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    private final int maxPeople;

    public BoatData(String name, int maxPeople) {
        this.name = name;
        this.maxPeople = maxPeople;
    }
}
