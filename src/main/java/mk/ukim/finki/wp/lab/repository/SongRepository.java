package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder.songList;
    }

    public Song findByTrackId(String trackId){
        return DataHolder.songList.stream().filter(r->r.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

//    public Artist addArtistToSong(Artist artist, Song song){
//        DataHolder.songList.stream().filter(r->r.getTrackId().equals(song.getTrackId()))
//                .forEach(r->r.addPerformer(artist));
//        return artist;
//    }

    public void deleteSong(Long id){
        DataHolder.songList.removeIf(r->r.getId().equals(id));
    }

    public Song findById(Long id){
        return DataHolder.songList.stream().filter(song -> song.getId().equals(id)).findFirst().orElse(null);
    }

    public void update(Song song){
        DataHolder.songList.removeIf(x->x.getId().equals(song.getId()));
        DataHolder.songList.add(song);
    }
}
