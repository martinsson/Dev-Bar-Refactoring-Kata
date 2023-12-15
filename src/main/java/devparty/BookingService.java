package devparty;

import devparty.repository.BarData;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {


    private final IBarRepository barRepo;
    private final IDevRepository devRepo;
    private final IBoatRepository boatRepo;
    private final IBookingRepository bookingRepo;

    public BookingService(IBarRepository barRepo, IDevRepository devRepo, IBoatRepository boatRepo, IBookingRepository bookingRepo) {
        this.barRepo = barRepo;
        this.devRepo = devRepo;
        this.boatRepo = boatRepo;
        this.bookingRepo = bookingRepo;
    }

    public boolean reserveBar() {
        var bars = barRepo.get();
        var devs = devRepo.get().stream().collect(Collectors.toList());
        var boats = boatRepo.get();

        Map<LocalDate, Integer> numberOfAvailableDevsByDate = new HashMap<>();
        for (var devData : devs) {
            for (var date : devData.getOnSite()) {
                if (numberOfAvailableDevsByDate.containsKey(date)) {
                    numberOfAvailableDevsByDate.put(date, numberOfAvailableDevsByDate.get(date) + 1);
                    continue;
                } else {
                    numberOfAvailableDevsByDate.put(date, 1);
                }
            }
        }

        int maxNumberOfDevs = Collections.max(numberOfAvailableDevsByDate.values());

        if (maxNumberOfDevs <= devs.size() * 0.6) {
            return false;
        }

        Optional<Map.Entry<LocalDate, Integer>> found = Optional.empty();
        for (Map.Entry<LocalDate, Integer> entry : numberOfAvailableDevsByDate.entrySet()) {
            if (entry.getValue() == maxNumberOfDevs) {
                found = Optional.of(entry);
                break;
            }
        }
        LocalDate bestDate = found
                .map(Map.Entry::getKey)
                .orElse(null);

        for (var boatData : boats) {
            Bar bar = new Bar();
            if (bar.hasEnoughCapacity(boatData, maxNumberOfDevs)) {
                bookBar(boatData.getName(), bestDate);
                BarData barData = new BarData(boatData.getName(), boatData.getMaxPeople(), allDays());
                bookingRepo.save(new BookingData(
                        barData, bestDate
                ));
                return true;
            }
        }

        for (var barData : bars) {
            if (barData.getCapacity() >= maxNumberOfDevs && Arrays.asList(barData.getOpen()).contains(bestDate.getDayOfWeek())) {
                bookBar(barData.getName(), bestDate);
                bookingRepo.save(new BookingData(barData, bestDate));
                return true;
            }
        }

        return false;
    }

    private static List<DayOfWeek> allDays() {
        return Arrays.asList(DayOfWeek.values());
    }

    private void bookBar(String name, LocalDate dateTime) {
        System.out.println("Bar booked: " + name + " at " + dateTime);
    }


}
