package mk.ukim.finki.wp.lab.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.impl.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.IWebSession;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@WebServlet(name="artist", urlPatterns = "/servlet/artist")
public class ArtistServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final ArtistServiceImpl artistService;

    public ArtistServlet(SpringTemplateEngine templateEngine,ArtistServiceImpl artistService) {
        this.templateEngine = templateEngine;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("artists",artistService.listArtists());

        this.templateEngine.process("artistsList.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId;
        List<Artist> artistList;
        artistList=artistService.listArtists();

        if (req.getParameter("songRadio") != null) {
            trackId = req.getParameter("songRadio");
        } else {
            trackId = "-";
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("trackId", trackId);
        context.setVariable("artists", artistList);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }
}
