package com.example.refactor.service;

import com.example.refactor.dto.SpotifyArtisDTO;
import com.example.refactor.dto.SpotifyDTO;
import com.example.refactor.dto.SpotifyTrackDTO;
import com.example.refactor.model.Artist;
import com.example.refactor.model.Song;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

//@RunWith(MockitoJUnitRunner.class)
public class SpotifySongProcessorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifySongProcessorTest.class);

    private SongProcessor songProcessor = new SpotifySongProcessor();

    @Test
    public void testProcessSongs() {

        songProcessor.processSongs();

    }

    @Test
    public void updateTestProcessorWithGSON() {

        final String playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");
        LOGGER.info("Json received " + playlistFileName);
        final File inputSource = ExampleFileUtils.getFileFromResources(playlistFileName);
        LOGGER.info("InputSource: " + inputSource.toString());
        final JSONObject playlist = ExampleFileUtils.getJsonFromFile(inputSource);
        LOGGER.info("Playlist: " + playlist.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<SpotifyDTO>> playlistMaObjectMapper = null;
        try {
            playlistMaObjectMapper = objectMapper.readValue(playlist.toString(), new TypeReference<Map<String, List<SpotifyDTO>>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<SpotifyDTO> spotifyDTOList = playlistMaObjectMapper.get("items");

        List<Song> songList = spotifyDTOList.stream().map(this::processDTOSong).collect(Collectors.toList());

        songList.forEach(song -> LOGGER.info(" - {} - {} - {} - {}", song.id(), song.name(),
                song.artist().name(), song.albumName()));

    }

    private Song processDTOSong(SpotifyDTO spotifyDTO) {
        SpotifyTrackDTO trackDTO = spotifyDTO.track();
        List<SpotifyArtisDTO> artistsList = trackDTO.artists();
        Song.Builder songBuilder = Song.builder().explicit(trackDTO.explicit()).
                id(trackDTO.id()).
                playable(trackDTO.is_playable()).
                name(trackDTO.name()).
                popularity(trackDTO.popularity()).
                albumType(trackDTO.album().get("album_type").toString()).
                albumId(trackDTO.album().get("id").toString()).
                albumName(trackDTO.album().get("name").toString()).
                albumReleaseDate(trackDTO.album().get("release_date").toString()).
                albumTotalTracks(trackDTO.album().get("total_tracks").toString());

        artistsList.forEach(artists ->{
            Artist artist = Artist.builder().
                    id(artists.id()).
                    name(artists.name()).
                    build();
            songBuilder.artist(artist);
        });

        return songBuilder.build();
    }


    @Test
    public void testDTOList() {

        final String playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");
        LOGGER.info("Json received " + playlistFileName);
        final File inputSource = ExampleFileUtils.getFileFromResources(playlistFileName);
        LOGGER.info("InputSource: " + inputSource.toString());
        final JSONObject playlist = ExampleFileUtils.getJsonFromFile(inputSource);
        LOGGER.info("Playlist: " + playlist.toString());


        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<SpotifyDTO>> playlistMaObjectMapper = null;
        try {
            playlistMaObjectMapper = objectMapper.readValue(playlist.toString(), new TypeReference<Map<String, List<SpotifyDTO>>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(playlistMaObjectMapper);


    }

}