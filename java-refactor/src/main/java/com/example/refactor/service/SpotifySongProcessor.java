package com.example.refactor.service;

import com.example.refactor.dto.SpotifyArtisDTO;
import com.example.refactor.dto.SpotifyDTO;
import com.example.refactor.dto.SpotifyTrackDTO;
import com.example.refactor.model.Artist;
import com.example.refactor.model.Song;
import com.example.refactor.util.ExampleFileUtils;
import com.example.refactor.util.PropertyFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * El nombre de la clase fue cambiado para especificar que
 * es procesador de canciones de spotify
 */
public class SpotifySongProcessor implements SongProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifySongProcessor.class);

    @Override
    public void processSongs() {
        final String playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");
        LOGGER.info("Json received " + playlistFileName);

        //Agregando Optional para la lectura
        //del objet que se recibe del archivo
        //en caso de que exista algùn error durante
        //el procesamiento del archivo o transformaciòn
        //del objeto, se devuelve una lista vacía
        //el uso del optional ayuda a manejar NullPointerException
        List<Song> songList = ExampleFileUtils.getFileFromResources(playlistFileName).
                map(ExampleFileUtils::getJsonFromFile).
                map(this::processJsonSongs).
                orElse(Collections.emptyList());

        songList.forEach(song -> LOGGER.info(" - {} - {} - {} - {}", song.getId(), song.getName(),
                song.getSpotifyArtist().getName(), song.getAlbumName()));
    }

    @Override
    public void processSongs(String playlistFileName) {

        LOGGER.info("Json received " + playlistFileName);

        //Agregando Optional para la lectura
        //del objet que se recibe del archivo
        //en caso de que exista algùn error durante
        //el procesamiento del archivo o transformaciòn
        //del objeto, se devuelve una lista vacía
        //el uso del optional ayuda a manejar NullPointerException
        List<Song> songList = ExampleFileUtils.getFileFromSystem(playlistFileName).
                map(ExampleFileUtils::getJsonFromFile).
                map(this::processJsonSongs).
                orElse(Collections.emptyList());

        songList.forEach(song -> LOGGER.info(" - {} - {} - {} - {}", song.getId(), song.getName(),
                song.getSpotifyArtist().getName(), song.getAlbumName()));

    }

    private List<Song> processJsonSongs(JSONObject jsonObject) {
        LOGGER.info("Playlist: " + jsonObject.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<SpotifyDTO>> playlistMaObjectMapper;
        try {
            playlistMaObjectMapper = objectMapper.readValue(jsonObject.toString(), new TypeReference<Map<String, List<SpotifyDTO>>>() {
            });
        } catch (JsonProcessingException e) {
            LOGGER.error("Invalid JSON object cast");
            return Collections.emptyList();
        }
        List<SpotifyDTO> spotifyDTOList = Optional.ofNullable(playlistMaObjectMapper.get("items")).orElse(Collections.emptyList());

        return spotifyDTOList.stream().map(this::processDTOSong).collect(Collectors.toList());
    }

    private Song processDTOSong(SpotifyDTO spotifyDTO) {
        SpotifyTrackDTO trackDTO = spotifyDTO.getTrack();
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

        artistsList.forEach(artists -> {
            Artist artist = Artist.builder().
                    id(artists.getId()).
                    name(artists.getName()).
                    build();
            songBuilder.artist(artist);
        });

        return songBuilder.build();
    }


}
