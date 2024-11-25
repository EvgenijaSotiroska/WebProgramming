package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {
    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public String songDetails(@RequestParam String trackID, @RequestParam String artistID, Model model) {
        Song song = songService.findByTrackId(trackID);
        Artist artist = artistService.findById(Long.valueOf(artistID)).orElse(null);
        songService.addArtistToSong(artist, song);
        model.addAttribute("song", song);

        return "songDetails";
    }
}