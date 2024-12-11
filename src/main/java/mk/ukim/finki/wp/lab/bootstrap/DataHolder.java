package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songList = new ArrayList<>();
    public static List<Album> albumList=new ArrayList<>();

    @PostConstruct
    public void init(){
        artistList.add(new Artist("Bruno","Mars",  "Bruno Mars bio"));
        artistList.add(new Artist("Alicia","Keys",  "Alicia Keys bio"));
        artistList.add(new Artist("Dua","Lipa",  "Dua Lipa bio"));
        artistList.add(new Artist("Justin","Timberlake",  "Justin Timberlake bio"));
        artistList.add(new Artist("Jennifer","Lopez", "Jennifer Lopez bio"));

        Album album1= new Album("Radical Optimism", 2024,"pop");
        albumList.add(album1);
        Album album2= new Album("24K Magic", 2016,"funk");
        albumList.add(album2);
        Album album3= new Album("Emotions", 1991,"pop");
        albumList.add(album3);
        Album album4= new Album("Red", 2012,"pop");
        albumList.add(album4);
        Album album5= new Album("Alpha", 2023,"pop");
        albumList.add(album5);

        songList.add(new Song("24K Magic", "pop", 2016, "111",album2));
        songList.add(new Song("In Common", "pop", 2016, "112", album3));
        songList.add(new Song("Houdini", "pop", 2024, "113",album1));
        songList.add(new Song("Mirrors", "pop", 2013, "114",album4));
        songList.add(new Song("Papi", "pop", 2011, "115",album5));
    }

}
