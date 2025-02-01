package com.example.refactor;

import com.example.refactor.service.SpotifySongProcessor;

public class Solution {
    public static void main(String... args) {
        SpotifySongProcessor spotifySongProcessor = new SpotifySongProcessor();
        spotifySongProcessor.processSongs();
    }
}
