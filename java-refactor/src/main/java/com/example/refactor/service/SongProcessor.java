package com.example.refactor.service;

import com.example.refactor.model.Song;
import com.example.refactor.model.SpotifyArtist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;

public class SongProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongProcessor.class);

    public void processSongs() {
        final String playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");

        final File inputSource = ExampleFileUtils.getFileFromResources(playlistFileName);
        final JSONObject playlist = ExampleFileUtils.getJsonFromFile(inputSource);
        final LinkedList<Song> spotifyPlayList = new LinkedList<>();

        final JSONArray items = (JSONArray) playlist.get("items");

        for (Object item : items) {
            JSONObject songJSON = (JSONObject) item;
            JSONObject trackJSON = (JSONObject) songJSON.get("track");
            JSONArray artistsJSON = (JSONArray) trackJSON.get("artists");
            JSONObject albumJSON = (JSONObject) trackJSON.get("album");

            Song song = new Song();
            song.setExplicit(trackJSON.get("explicit").toString());
            song.setId(trackJSON.get("id").toString());
            song.setPlayable(trackJSON.get("is_playable").toString());
            song.setName(trackJSON.get("name").toString());
            song.setPopularity(trackJSON.get("popularity").toString());
            song.setAlbumType(albumJSON.get("album_type").toString());
            song.setAlbumId(albumJSON.get("id").toString());
            song.setAlbumName(albumJSON.get("name").toString());
            song.setAlbumReleaseDate(albumJSON.get("release_date").toString());
            song.setAlbumTotalTracks(albumJSON.get("total_tracks").toString());

            for (Object element : artistsJSON) {
                JSONObject artistJSON = (JSONObject) element;

                SpotifyArtist artist = new SpotifyArtist();
                artist.setId(artistJSON.get("id").toString());
                artist.setName(artistJSON.get("name").toString());
                song.setSpotifyArtist(artist);
            }

            spotifyPlayList.add(song);
        }

        for (Song song : spotifyPlayList) {
            LOGGER.info(" - {} - {} - {} - {}", song.getId(), song.getName(),
                                                song.getSpotifyArtist().getName(), song.getAlbumName());
        }
    }

}
