package com.example.refactor.model;

public record Song(String id,
                   String name,
                   Boolean explicit,
                   Boolean playable,
                   Integer popularity,
                   String albumId,
                   String albumType,
                   String albumName,
                   String albumReleaseDate,
                   String albumTotalTracks,
                   Artist artist){

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String name;
        private Boolean explicit;
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

        public Builder explicit(Boolean explicit) {
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

            return new Song(id,
                    name,
                    explicit,
                    playable,
                    popularity,
                    albumId,
                    albumType,
                    albumName,
                    albumReleaseDate,
                    albumTotalTracks,
                    artist);
        }

    }

}
