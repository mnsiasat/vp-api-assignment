package com.vp.api.service;


import com.vp.api.controller.MoviesController;
import com.vp.api.model.Movie;
import com.vp.api.repository.MoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MoviesService {

    @Autowired
    private MoviesRepository repository;

    public Movie addMovie(Movie movie) {
        return repository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return repository.findAll();
    }

    public Movie updateMovie(String id, Movie newMovie){
        return repository.findById(id)
                .map(movie -> {
                    if(newMovie.getComment()!=null){
                        movie.setComment(newMovie.getComment());
                    }
                    if(newMovie.getImage() != null){
                        movie.setImage(newMovie.getImage());
                    }
                    movie.setWatched(newMovie.isWatched());
                    if(newMovie.getTitle()!= null){
                        movie.setTitle(newMovie.getTitle());
                    }
                    return repository.save(movie);
                }).orElse(null);
    }

    public void deleteMovie(String id) {
        repository.deleteById(id);
    }

    public List<Movie> getWatchedMovies(){
        return repository.findByWatched(true);
    }
}
