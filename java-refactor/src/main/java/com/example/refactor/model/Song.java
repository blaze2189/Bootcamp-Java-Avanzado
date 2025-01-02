package com.example.refactor.model;

public class Song {

    private String id;
    private String name;
    private String explicit;
    private String playable;
    private String popularity;

    private String albumId;
    private String albumType;
    private String albumName;
    private String albumReleaseDate;
    private String albumTotalTracks;

    public SpotifyArtist spotifyArtist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplicit() {
        return explicit;
    }

    public void setExplicit(String explicit) {
        this.explicit = explicit;
    }

    public String getPlayable() {
        return playable;
    }

    public void setPlayable(String playable) {
        this.playable = playable;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(String albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }

    public String getAlbumTotalTracks() {
        return albumTotalTracks;
    }

    public void setAlbumTotalTracks(String albumTotalTracks) {
        this.albumTotalTracks = albumTotalTracks;
    }

    public SpotifyArtist getSpotifyArtist() {
        return spotifyArtist;
    }

    public void setSpotifyArtist(SpotifyArtist spotifyArtist) {
        this.spotifyArtist = spotifyArtist;
    }
}
