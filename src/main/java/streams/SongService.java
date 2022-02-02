package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SongService {
    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public Optional<Song> shortestSong() {
        return songs.stream()
                .sorted(Comparator.comparingInt(Song::getLength))
                .findFirst();
    }

    public List<Song> findSongByTitle(String title) {
        return songs.stream()
                .filter(s -> title.equals(s.getTitle()))
                .toList();
    }

    public boolean isPerformerInSong(Song song, String performer) {
        return songs.stream()
                .filter(s -> s.equals(song))
                .anyMatch(s -> s.getPerformers().contains(performer));
    }

    public List<String> titlesBeforeDate(LocalDate releasedBefore) {
        return songs.stream()
                .filter(s -> s.getRelease().isBefore(releasedBefore))
                .map(s -> s.getTitle())
                .toList();
    }
}
