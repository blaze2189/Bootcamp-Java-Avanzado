package com.example.refactor.service;

import com.example.refactor.model.Song;
import com.example.refactor.model.Artist;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;

/**
* El nombre de la clase fue cambiado para especificar que
 * es procesador de canciones de spotify
* */
public class SpotifySongProcessor implements SongProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifySongProcessor.class);

    @Override
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

            Song.Builder songBuilder = Song.builder();
            songBuilder.explicit(trackJSON.get("explicit").toString());
            songBuilder.id(trackJSON.get("id").toString());
            songBuilder.playable(trackJSON.get("is_playable").toString());
            songBuilder.name(trackJSON.get("name").toString());
            songBuilder.popularity(trackJSON.get("popularity").toString());
            songBuilder.albumType(albumJSON.get("album_type").toString());
            songBuilder.albumId(albumJSON.get("id").toString());
            songBuilder.albumName(albumJSON.get("name").toString());
            songBuilder.albumReleaseDate(albumJSON.get("release_date").toString());
            songBuilder.albumTotalTracks(albumJSON.get("total_tracks").toString());

            for (Object element : artistsJSON) {
                JSONObject artistJSON = (JSONObject) element;

                //Generación de instancia mediante Builer
                Artist artist = Artist.builder().
                        id(artistJSON.get("id").toString()).
                        name(artistJSON.get("name").toString()).
                        build();
                songBuilder.artist(artist);
            }

            spotifyPlayList.add(songBuilder.build());
        }

        for (Song song : spotifyPlayList) {
            LOGGER.info(" - {} - {} - {} - {}", song.getId(), song.getName(),
                                                song.getSpotifyArtist().getName(), song.getAlbumName());
        }
    }

}
