package vehiclerental;

import java.time.LocalTime;

public class Bike implements Rentable {
    private String id;
    private LocalTime rentingTime;
    public static final int COST_PER_MINUTE = 15;

    public Bike(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * COST_PER_MINUTE;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;
    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }
}
