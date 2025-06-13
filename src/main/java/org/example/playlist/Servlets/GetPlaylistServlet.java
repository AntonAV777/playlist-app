package org.example.playlist.Servlets;

import org.example.playlist.Model.Song;
import org.example.playlist.Service.PlaylistService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/playlist")
public class GetPlaylistServlet extends HttpServlet {

    private final PlaylistService playlistService = new PlaylistService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("email") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("User not identified");
            return;
        }

        String email = (String) session.getAttribute("email");
        List<Song> songs = playlistService.getSongs(email);

        response.setContentType("application/json");
        StringBuilder json = new StringBuilder("[");

        for (int i = 0; i < songs.size(); i++) {
            Song s = songs.get(i);
            json.append("{\"title\":\"").append(s.getTitle())
                    .append("\",\"artist\":\"").append(s.getArtist()).append("\"}");
            if (i < songs.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");
        response.getWriter().write(json.toString());
    }
}