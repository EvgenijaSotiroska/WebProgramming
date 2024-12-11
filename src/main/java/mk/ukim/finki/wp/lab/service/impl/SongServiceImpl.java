package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistJpaRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongJpaRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    private final ArtistJpaRepository artistRepository;
    private final SongJpaRepository songRepository;

    public SongServiceImpl(ArtistJpaRepository artistRepository, SongJpaRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        return songRepository.save(song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }
    public List<Song> findSongByText(String text){
        return  songRepository.findAll().stream().
                filter(r->r.getTitle().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());

    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void updateSong(Song song) {
        songRepository.delete(song);
        songRepository.save(song);
    }

    @Override
    public List<Song> findByAlbum(Long id) {
        return songRepository.findAllByAlbum_Id(id);
    }
}
