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
<<<<<<< HEAD
<<<<<<< HEAD
public interface SongProcessor {
=======
public sealed interface SongProcessor permits  SpotifySongProcessor{
>>>>>>> 81125c4 (Sealed classes)
=======
public sealed interface SongProcessor permits  SpotifySongProcessor{
>>>>>>> 364f2db (Sealed classes)
    void processSongs();

    void processSongs(String pathToFile);
}
