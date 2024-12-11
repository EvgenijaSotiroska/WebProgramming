
package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;

   @ManyToMany
   private List<Artist> performers;

    @ManyToOne
    private Album album;

    public Song(String title, String genre, int releaseYear,  String trackId, Album album) {
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.title = title;
        this.trackId = trackId;
        this.album=album;
        this.performers=new ArrayList<>();
    }

    public Song(String title, String genre, int releaseYear,  String trackId, Album album, Long id) {
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.title = title;
        this.trackId = trackId;
        this.id=id;
        this.album=album;
        this.performers=new ArrayList<>();
    }

    public void addPerformer(Artist performer){
        performers.removeIf(r->r.getId().equals(performer.getId()));
        performers.add(performer);
    }
}
