package mk.ukim.finki.wp.lab.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.WebConnection;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="songServlet", urlPatterns = "/servlet/listSongs")
public class SongListServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final SongServiceImpl songService;

    public SongListServlet(SpringTemplateEngine templateEngine, SongServiceImpl songService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange= JakartaServletWebApplication.buildApplication(req.getServletContext()).
                buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("songs", songService.listSongs());
        this.templateEngine.process("listSongs.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text=req.getParameter("searchSong");
        List<Song> songsByText=this.songService.findSongByText(text);
        IWebExchange iWebExchange= JakartaServletWebApplication.buildApplication(req.getServletContext()).
                buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("songs", songsByText);
        this.templateEngine.process("listSongs.html",context, resp.getWriter());
    }
}
