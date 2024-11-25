package mk.ukim.finki.wp.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private Long id;
    private String name;
    private String genre;
    private int releaseYear;

    public Album(String name, int releaseYear, String genre) {
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.name = name;
        this.id=(long) (Math.random()*1000);
    }
}
