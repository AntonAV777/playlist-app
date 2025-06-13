package org.example.playlist.Servlets;

import org.example.playlist.Model.Song;
import org.example.playlist.Service.PlaylistService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addSong")
public class AddSongServlet extends HttpServlet {

    private final PlaylistService playlistService = new PlaylistService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("email") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("User not identified");
            return;
        }

        String email = (String) session.getAttribute("email");
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");

        if (title == null || title.isEmpty() || artist == null || artist.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Title and artist are required");
            return;
        }

        Song song = new Song(title, artist);
        playlistService.addSong(email, song);
        response.getWriter().write("Song added");
    }
}