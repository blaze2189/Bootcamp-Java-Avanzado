package com.example.refactor.service;

/**
 * Creaciòn de interfaz para especificar comportamientos
 * de un procesador de canciones
 * */
public interface SongProcessor {
    void processSongs();

    void processSongs(String pathToFile);
}
