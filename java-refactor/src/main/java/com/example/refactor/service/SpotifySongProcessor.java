package com.example.refactor.service;

import com.example.refactor.dto.SpotifyArtisDTO;
import com.example.refactor.dto.SpotifyDTO;
import com.example.refactor.model.Artist;
import com.example.refactor.model.Song;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* El nombre de la clase fue cambiado para especificar que
 * es procesador de canciones de spotify
* */
public class SpotifySongProcessor implements SongProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifySongProcessor.class);

    @Override
    public void processSongs() {
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
        // List<SpotifyDTO> spotifyDTOList = playlistMap.get("items");
        List<SpotifyDTO> spotifyDTOList = playlistMaObjectMapper.get("items");

        List<Song> songList = spotifyDTOList.stream().map(this::processDTOSong).collect(Collectors.toList());

        songList.forEach(song -> LOGGER.info(" - {} - {} - {} - {}", song.getId(), song.getName(),
                song.getSpotifyArtist().getName(), song.getAlbumName()));
    }

    private Song processDTOSong(SpotifyDTO spotifyDTO) {
        SpotifyDTO.SpotifyTrackDTO trackDTO = spotifyDTO.getTrack();
        List<SpotifyArtisDTO> artistsList = trackDTO.getArtists();
        Song.Builder songBuilder = Song.builder().explicit(trackDTO.getExplicit()).
                id(trackDTO.getId()).
                playable(trackDTO.getIs_playable()).
                name(trackDTO.getName()).
                popularity(trackDTO.getPopularity()).
                albumType(trackDTO.getAlbum().get("album_type").toString()).
                albumId(trackDTO.getAlbum().get("id").toString()).
                albumName(trackDTO.getAlbum().get("name").toString()).
                albumReleaseDate(trackDTO.getAlbum().get("release_date").toString()).
                albumTotalTracks(trackDTO.getAlbum().get("total_tracks").toString());

        artistsList.forEach(artists ->{
            Artist artist = Artist.builder().
                    id(artists.getId()).
                    name(artists.getName()).
                    build();
            songBuilder.artist(artist);
        });

        return songBuilder.build();
    }


}
