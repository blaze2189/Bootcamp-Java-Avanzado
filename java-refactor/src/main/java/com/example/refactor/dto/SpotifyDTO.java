package com.example.refactor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyDTO {

    private SpotifyTrackDTO track;

    public SpotifyTrackDTO getTrack() {
        return track;
    }

    public void setTrack(SpotifyTrackDTO track) {
        this.track = track;
    }

    public static class SpotifyTrackDTO {
        private Map<String,Object> album;
        private List<Map<String,Object>> external_urls;
        private Boolean explicit;
        private String id;
        private Boolean is_playable;
        private String name;
        private Integer popularity;
        private List<SpotifyArtisDTO> artists;

        public List<SpotifyArtisDTO> getArtists() {
            return artists;
        }

        public void setArtists(List<SpotifyArtisDTO> artists) {
            this.artists = artists;
        }

        public Map<String, Object> getAlbum() {
            return album;
        }

        public void setAlbum(Map<String, Object> album) {
            this.album = album;
        }

        public List<Map<String, Object>> getExternal_urls() {
            return external_urls;
        }

        public void setExternal_urls(List<Map<String, Object>> external_urls) {
            this.external_urls = external_urls;
        }

        public String getExplicit() {
            return explicit.toString();
        }

        public void setExplicit(Boolean explicit) {
            this.explicit = explicit;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Boolean getIs_playable() {
            return is_playable;
        }

        public void setIs_playable(Boolean is_playable) {
            this.is_playable = is_playable;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPopularity() {
            return popularity;
        }

        public void setPopularity(Integer popularity) {
            this.popularity = popularity;
        }
    }
}
