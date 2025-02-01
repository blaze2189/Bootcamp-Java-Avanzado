package com.example.refactor.model;

import java.util.List;

/**
 * Aplicaciòn de Builder pattern
 * La clase se vulve inmutable al eliminar los setters
 * */
public class Artist {
    private final String id;
    private final String name;
    //Declaración de varialbes por interfaz y no por implmentaciones
    //Actualizadción de nombre de atributo a un nombre que genere màs cohesión
    private final List<Artist> artistList;

    private Artist(String id, String name, List<Artist> artistList){
        this.id=id;
        this.name=name;
        this.artistList = artistList;
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public static class Builder{

        private String id;
        private String name;
        private List<Artist> artistList;

        private Builder(){};

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name=name;
            return this;
        }

        public Builder genres(List<Artist> artistList){
            this.artistList =artistList;
            return this;
        }

        public Artist build(){
            return new Artist(id,name, artistList);
        }

    }

}