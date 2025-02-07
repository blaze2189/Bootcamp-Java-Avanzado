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
}
