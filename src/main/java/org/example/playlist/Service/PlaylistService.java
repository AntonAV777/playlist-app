package org.example.playlist.Service;

import org.example.playlist.Model.Song;

import java.util.*;

public class PlaylistService {

    private static final Map<String, List<Song>> playlists = new HashMap<>();

    public void addSong(String email, Song song) {
        playlists
                .computeIfAbsent(email, key -> new ArrayList<>())
                .add(song);
    }

    public void removeSong(String email, String title) {
        List<Song> songs = playlists.get(email);
        if (songs != null) {
            songs.removeIf(song -> title.equalsIgnoreCase(song.getTitle()));
        }
    }

    public List<Song> getSongs(String email) {
        return playlists.getOrDefault(email, Collections.emptyList());
    }
}
