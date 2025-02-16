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
 *
 * Implmenta una selead class por lo cual se declara como final
 * ninguna otra clase podrà heredar de SpotifySongProcessor
* */
public final class SpotifySongProcessor implements SongProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpotifySongProcessor.class);

    @Override
    public void processSongs() {
        final var playlistFileName = PropertyFactory.getProperties().getProperty("refactorpractice.playlist.filename");
        LOGGER.info("Json received " + playlistFileName);

        //Agregando Optional para la lectura
        //del objet que se recibe del archivo
        //en caso de que exista algùn error durante
        //el procesamiento del archivo o transformaciòn
        //del objeto, se devuelve una lista vacía
        //el uso del optional ayuda a manejar NullPointerException
        var songList = ExampleFileUtils.getFileFromResources(playlistFileName).
                map(ExampleFileUtils::getJsonFromFile).
                map(this::processJsonSongs).
                orElse(Collections.emptyList());

        songList.forEach(song -> LOGGER.info(" - {} - {} - {} - {}", song.id(), song.name(),
                song.artist().name(), song.albumName()));
    }

    private List<Song> processJsonSongs(JSONObject jsonObject) {
        LOGGER.info("Playlist: " + jsonObject.toString());

        var objectMapper = new ObjectMapper();
        Map<String, List<SpotifyDTO>> playlistMaObjectMapper;
        try {
            playlistMaObjectMapper = objectMapper.
                    readValue(jsonObject.toString(), new TypeReference<Map<String, List<SpotifyDTO>>>() {});
        } catch (JsonProcessingException e) {
            LOGGER.error("Invalid JSON object cast");

            return Collections.emptyList();
        }
        var spotifyDTOList = Optional.ofNullable(playlistMaObjectMapper.get("items")).orElse(Collections.emptyList());

        return spotifyDTOList.stream().map(this::processDTOSong).toList();
    }

    private Song processDTOSong(SpotifyDTO spotifyDTO) {
        var trackDTO = spotifyDTO.track();
        var artistsList = trackDTO.artists();
        var songBuilder = Song.builder().explicit(trackDTO.explicit()).
                id(trackDTO.id()).
                playable(trackDTO.is_playable()).
                name(trackDTO.name()).
                popularity(trackDTO.popularity()).
                albumType(trackDTO.album().album_type()).
                albumId(trackDTO.album().id()).
                albumName(trackDTO.album().name()).
                albumReleaseDate(trackDTO.album().release_date()).
                albumTotalTracks(trackDTO.album().total_tracks());

        artistsList.forEach(artists -> {
            Artist artist = Artist.builder().
                    id(artists.id()).
                    name(artists.name()).
                    build();
            songBuilder.artist(artist);
        });

        return songBuilder.build();
    }


}
