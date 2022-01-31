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

    }
}
