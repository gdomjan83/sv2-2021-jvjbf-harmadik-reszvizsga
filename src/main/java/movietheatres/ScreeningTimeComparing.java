package movietheatres;

import java.util.Comparator;

public class ScreeningTimeComparing implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
