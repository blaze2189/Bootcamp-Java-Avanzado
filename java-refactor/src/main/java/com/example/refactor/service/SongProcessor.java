package com.example.refactor.service;

/**
 * Creaciòn de interfaz para especificar comportamientos
 * de un procesador de canciones
 *
 * Utilización de sealed clases, solo las clases que
 * tengan como objetivo procesar canciones deben
 * implmentar esta interfaz y por lo tanto serán
 * especificadas en el permits
 * */
public sealed interface SongProcessor permits  SpotifySongProcessor{ void processSongs();

    void processSongs(String pathToFile);
}
