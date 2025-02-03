package com.example.refactor.service;

import com.example.refactor.model.Artist;
import com.example.refactor.model.Song;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//@RunWith(MockitoJUnitRunner.class)
public class SpotifySongProcessorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifySongProcessorTest.class);

    private SongProcessor songProcessor = new SpotifySongProcessor();

    @Test
    public void testProcessSongs() {

        songProcessor.processSongs();

    }

    @Test
    public void updateTestProcessor() {

        final String playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");
        LOGGER.info("Json received " + playlistFileName);
        final File inputSource = ExampleFileUtils.getFileFromResources(playlistFileName);
        LOGGER.info("InputSource: " + inputSource.toString());
        final JSONObject playlist = ExampleFileUtils.getJsonFromFile(inputSource);
        LOGGER.info("Playlist: " + playlist.toString());

        Gson gson = new Gson();
        Map<String, Object> playlisMap = gson.fromJson(playlist.toString(), Map.class);
        playlisMap.keySet().stream().forEach(LOGGER::info);
        List<Map<String, Map<String, Object>>> items = (List) playlisMap.get(playlisMap.keySet().stream().findFirst().get());


        //Map<String, Map<String, Object>> track = items.get(0);
        Map<String, Map<String, Object>> item = items.get(0);

        Map<String, Object> track = (Map) item.get("track");
        List<Map<String, Object>> artists = (List) track.get("artists");
        Map<String, Object> album = (Map) track.get("album");

        // Map<String, Object> song = item.get("artists");
        LOGGER.info("Total items: " + items.size());
        LOGGER.info("artist size " + artists.size());

        Song.Builder songBuilder = Song.builder();
        songBuilder.explicit(track.get("explicit").toString());
        songBuilder.id(track.get("id").toString());
        songBuilder.playable(track.get("is_playable").toString());
        songBuilder.name(track.get("name").toString());
        songBuilder.popularity(track.get("popularity").toString());
        songBuilder.albumType(album.get("album_type").toString());
        songBuilder.albumId(album.get("id").toString());
        songBuilder.albumName(album.get("name").toString());
        songBuilder.albumReleaseDate(album.get("release_date").toString());
        songBuilder.albumTotalTracks(album.get("total_tracks").toString());
        
        artists.stream().forEach(a -> {
                    Artist songArtist = Artist.builder().
                            id(a.get("id").toString()).
                            name(a.get("name").toString()).
                            build();

                    songBuilder.artist(songArtist);
                }
        );

        Song song = songBuilder.build();
        LOGGER.info(song.getName());

        LOGGER.info(" - {} - {} - {} - {}", song.getId(), song.getName(),
                song.getSpotifyArtist().getName(), song.getAlbumName());

//        artists.keySet().stream().forEach(key -> LOGGER.info(artists.get(key).toString()));
        // Map<String, Object> album = song.get("album");



        /*final File inputSource = ExampleFileUtils.getFileFromResources(playlistFileName);
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
        }*/


    }

}