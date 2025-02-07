package com.example.refactor.model;

public class Song {

    private String id;
    private String name;
    private String explicit;
    private Boolean playable;
    private Integer popularity;

    private String albumId;
    private String albumType;
    private String albumName;
    private String albumReleaseDate;
    private String albumTotalTracks;

    //Modificaciòn del modificador de acceso a privado
    private Artist artist;

    private Song(){}

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getExplicit() {
        return explicit;
    }

    private void setExplicit(String explicit) {
        this.explicit = explicit;
    }

    public Boolean getPlayable() {
        return playable;
    }

    private void setPlayable(Boolean playable) {
        this.playable = playable;
    }

    public Integer getPopularity() {
        return popularity;
    }

    private void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getAlbumId() {
        return albumId;
    }

    private void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumType() {
        return albumType;
    }

    private void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getAlbumName() {
        return albumName;
    }

    private void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    private void setAlbumReleaseDate(String albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }

    public String getAlbumTotalTracks() {
        return albumTotalTracks;
    }

    private void setAlbumTotalTracks(String albumTotalTracks) {
        this.albumTotalTracks = albumTotalTracks;
    }

    public Artist getSpotifyArtist() {
        return artist;
    }

    private void setArtist(Artist artist){
        this.artist=artist;
    }

    private void setSpotifyArtist(Artist artist) {
        this.artist = artist;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String name;
        private String explicit;
        private Boolean playable;
        private Integer popularity;

        private String albumId;
        private String albumType;
        private String albumName;
        private String albumReleaseDate;
        private String albumTotalTracks;

        private Artist artist;

        private Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder explicit(String explicit) {
            this.explicit = explicit;
            return this;
        }

        public Builder playable(Boolean playable) {
            this.playable = playable;
            return this;
        }

        public Builder popularity(Integer popularity) {
            this.popularity = popularity;
            return this;
        }


        public Builder albumId(String albumId) {
            this.albumId = albumId;
            return this;
        }

        public Builder albumType(String albumType) {
            this.albumType = albumType;
            return this;
        }

        public Builder albumName(String albumName) {
            this.albumName = albumName;
            return this;
        }

        public Builder albumReleaseDate(String albumReleaseDate) {
            this.albumReleaseDate = albumReleaseDate;
            return this;
        }

        public Builder artist(Artist artist){
            this.artist=artist;
            return this;
        }

        public Builder albumTotalTracks(String albumTotalTracks) {
            this.albumTotalTracks = albumTotalTracks;
            return this;
        }

        public Song build(){
            Song song = new Song();

            song.setId(id);
            song.setName(name);
            song.setExplicit(explicit);
            song.setPlayable(playable);
            song.setPopularity(popularity);
            song.setAlbumId(albumId);
            song.setAlbumType(albumType);
            song.setAlbumName(albumName);
            song.setAlbumReleaseDate(albumReleaseDate);
            song.setAlbumTotalTracks(albumTotalTracks);
            song.setArtist(artist);

            return song;
        }

    }

}
