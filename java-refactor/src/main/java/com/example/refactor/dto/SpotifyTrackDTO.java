package com.example.refactor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SpotifyTrackDTO(Map<String,Object> album,
                              List<Map<String,Object>> external_urls,
                              Boolean explicit,
                              String id,
                              Boolean is_playable,
                              String name,
                              Integer popularity,
                              List<SpotifyArtisDTO> artists) {

}
