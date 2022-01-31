package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {
    private Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public Map<String, List<Movie>> getShows() {
        return new LinkedHashMap<>(shows);
    }

    public void readFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                fillMap(line);
            }
            sortMap(shows);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read file.", ioe);
        }
    }

    public List<String> findMovie(String title) {
        Set<String> result = new HashSet<>();
        for (Map.Entry<String, List<Movie>> actual : shows.entrySet()) {
            for (Movie movie : actual.getValue()) {
                if (title.equals(movie.getTitle())) {
                    result.add(actual.getKey());
                }
            }
        }
        return new ArrayList<>(result);
    }

    private void fillMap(String line) {
        String theatre = createTheatre(line);
        Movie movie = createMovie(line);
        if (!shows.containsKey(theatre)) {
            shows.put(theatre, new ArrayList<>());
        }
        shows.get(theatre).add(movie);
    }

    private void sortMovies(List<Movie> movies) {
        movies.sort(new ScreeningTimeComparing());
    }

    private void sortMap(Map<String, List<Movie>> shows) {
        for (List<Movie> actual : shows.values()) {
            sortMovies(actual);
        }
    }

    public LocalTime findLatestShow(String title) {
        return LocalTime.now(); //nem tudtam befejzeni
    }

    private String createTheatre(String line) {
        int index = line.indexOf("-");
        return line.substring(0, index);
    }

    private Movie createMovie(String line) {
        int index = line.indexOf("-");
        String[] data = line.substring(index + 1).split(";");
        return new Movie(data[0], LocalTime.parse(data[1]));
    }





}
