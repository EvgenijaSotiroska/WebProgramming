package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> performers;


    private Album album;

    public Song(String title, String genre, int releaseYear,  String trackId, Album album) {
        this.genre = genre;
        this.performers = new ArrayList<>();
        this.releaseYear = releaseYear;
        this.title = title;
        this.trackId = trackId;
        this.id=(long) (Math.random()*1000);
        this.album=album;
    }

    public Song(String title, String genre, int releaseYear,  String trackId, Album album, Long id) {
        this.genre = genre;
        this.performers = new ArrayList<>();
        this.releaseYear = releaseYear;
        this.title = title;
        this.trackId = trackId;
        this.id=id;
        this.album=album;
    }

    public void addPerformer(Artist performer){
        performers.removeIf(r->r.getId().equals(performer.getId()));
        performers.add(performer);
    }
}
