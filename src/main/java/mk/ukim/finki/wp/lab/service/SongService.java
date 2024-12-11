package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    Song addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    List<Song> findSongByText(String text);
    void deleteById(Long id);
    Optional<Song> findById(Long id);
    void updateSong(Song song);
    List<Song> findByAlbum(Long id);
}