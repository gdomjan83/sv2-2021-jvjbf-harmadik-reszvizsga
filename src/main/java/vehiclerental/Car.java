package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable {
    private String id;
    private LocalTime rentingTime;
    private int costPerMinute;

    public Car(String id, int costPerMinute) {
        this.id = id;
        this.costPerMinute = costPerMinute;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) minutes * costPerMinute;
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
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
