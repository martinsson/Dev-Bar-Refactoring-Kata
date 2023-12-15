package devparty.model;

import devparty.model.BoatData;

public class Bar {
    public boolean hasEnoughCapacity(BoatData boatData, int maxNumberOfDevs) {
        return boatData.getMaxPeople() >= maxNumberOfDevs;
    }
}