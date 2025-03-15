package com.example.refactor;

import com.example.refactor.service.SongProcessor;
import com.example.refactor.service.SpotifySongProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Solution {
    public static void main(String... args) {

        String playlistFileName =args.length>0?args[0]:getFileNameFromConsole();

        //Se agrega opción para ingresar desde consola el nombre del archivo
        /*String playlistFileName = Optional
                .of(args[0])
                .orElse(getFileNameFromConsole());*/

        SongProcessor spotifySongProcessor = new SpotifySongProcessor();
        spotifySongProcessor.processSongs(playlistFileName.trim());
    }

    private static String getFileNameFromConsole() {
        String mensajeEnConsola = "Ingresar ruta del archivo: ";
        System.out.println(mensajeEnConsola);

        BufferedReader r = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            return r.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
