package com.example.refactor.model;

import java.util.List;

/**
 * Aplicaciòn de Builder pattern
 * La clase se vulve inmutable al eliminar los setters
 * */
public record Artist (String id,
                     String name,
                     List<Artist> artistList) {

    public static Builder builder(){
        return new Builder();
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