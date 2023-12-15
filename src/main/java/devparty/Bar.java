package devparty;

import devparty.repository.BoatData;

public class Bar {
    public boolean hasEnoughCapacity(BoatData boatData, int maxNumberOfDevs) {
        return boatData.getMaxPeople() >= maxNumberOfDevs;
    }
}