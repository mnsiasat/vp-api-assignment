package com.vp.api.controller;

import com.vp.api.model.Movie;

public record MovieRequest(String title, String image, String comment, boolean watched){

    public Movie toMovieModel(){
        return Movie.builder().title(title).image(image).comment(comment).build();
    }
}
