package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {
    private Set<Rentable> rentables = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>();

    public Set<Rentable> getRentables() {
        return new HashSet<>(rentables);
    }

    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    public Map<Rentable, User> getActualRenting() {
        return new TreeMap<>(actualRenting);
    }

    public void registerUser(User user) {
        if (users.contains(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }

    public void addRentable(Rentable vehicle) {
        rentables.add(vehicle);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        validateRentingParameters(user, rentable, time);
        rentable.rent(time);
        actualRenting.put(rentable, user);
    }

    public void closeRent(Rentable rentable, int minutes) {
        if (!actualRenting.containsKey(rentable)) {
            throw new IllegalStateException("Vehicle is not in the system.");
        }
        int cost = rentable.calculateSumPrice(minutes);
        actualRenting.get(rentable).minusBalance(cost);
        actualRenting.remove(rentable);
        rentable.closeRent();
    }

    private void validateRentingParameters(User user, Rentable rentable, LocalTime time) {
        if (rentable.getRentingTime() != null) {
            throw new IllegalStateException("Vehicle is already being rent.");
        }

        if (time.isBefore(LocalTime.now())) {
            throw new IllegalStateException("You can not start renting in the past.");
        }

        if (user.getBalance() < rentable.calculateSumPrice(180)) {
            throw new IllegalStateException("User doesn't have enough money.");
        }
    }
}
