package org.example.playlist.Servlets;

import org.example.playlist.Service.PlaylistService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/removeSong")
public class RemoveSongServlet extends HttpServlet {

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

        if (title == null || title.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Title is required");
            return;
        }

        playlistService.removeSong(email, title);
        response.getWriter().write("Song removed");
    }
}