package mk.ukim.finki.wp.lab.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="details", urlPatterns = "/servlet/songDetails")
public class SongDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine, SongService songService) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Song s = songService.findByTrackId("111");

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("s", s);
        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId=req.getParameter("trackId");
        String artistId=req.getParameter("artistId");
        Song s= songService.listSongs().stream().findFirst().orElse(null);


        if(trackId != null && artistId != null){
            s=songService.findByTrackId(trackId);
            Artist a=artistService.findById(Long.valueOf(artistId)).orElse(null);
            s.addPerformer(a);
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("s", s);
        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
