package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        List<Song> songList=songService.listSongs();
        model.addAttribute("songs", songList);
        return "listSongs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{songId}")
    public String editSong(@PathVariable Long songId, Model model){
        Song song = songService.findById(songId).orElse(new Song());
//        if (song.isEmpty()) {
//            return "redirect:/songs";
//        }
        model.addAttribute("song", song);
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }

    @PostMapping("/edit/{id}")
    public String edit( @PathVariable Long id,@RequestParam String title, @RequestParam String genre, Model model,
                        @RequestParam int releaseYear, @RequestParam Long albumId, @RequestParam String trackId ){
        Optional<Album> album = albumService.findById(albumId);
        if (album.isEmpty() || trackId.isEmpty() || title.isEmpty() || genre.isEmpty() || releaseYear < 0 || albumId < 0)  {
            model.addAttribute("albums", albumService.findAll());
            model.addAttribute("error", "Populate all fields");
            return "add-song";
        }
        songService.updateSong(new Song(title, genre, releaseYear,  trackId, album.get(), id));
        return "redirect:/songs";
    }

    @GetMapping("/add")
    public String addSong(Model model){
        List<Album> albums= albumService.findAll();
        model.addAttribute("albums", albums);
        return "add-song";
    }

    @GetMapping("/search")
    public String searchSong(@RequestParam Long album_id, Model model){
        List<Song> songs= songService.findByAlbum(album_id);
        model.addAttribute("songs", songs);
        return "listSongs";
    }

    @PostMapping("/save")
    public String saveSong(@RequestParam String title, @RequestParam String genre, Model model,
                           @RequestParam int releaseYear, @RequestParam Long albumId, @RequestParam String trackId){
        Optional<Album> album = albumService.findById(albumId);
        if (album.isEmpty() || trackId.isEmpty() || title.isEmpty() || genre.isEmpty() || releaseYear < 0 || albumId < 0)  {
            model.addAttribute("albums", albumService.findAll());
            model.addAttribute("error", "Populate all fields");
            return "add-song";
        }
        songService.updateSong(new Song(title, genre, releaseYear,  trackId, album.get()));
        return "redirect:/songs";
    }
}
