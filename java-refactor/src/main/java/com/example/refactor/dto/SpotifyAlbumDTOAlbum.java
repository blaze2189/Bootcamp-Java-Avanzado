package com.example.refactor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
* albumType(trackDTO.album().get("album_type").toString()).
                albumId(trackDTO.album().get("id").toString()).
                albumName(trackDTO.album().get("name").toString()).
                albumReleaseDate(trackDTO.album().get("release_date").toString()).
                albumTotalTracks(trackDTO.album().get("total_tracks").toString());
* */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SpotifyAlbumDTOAlbum (String album_type,
                                   String id,
                                   String name,
                                   String release_date,
                                   String total_tracks){
}
