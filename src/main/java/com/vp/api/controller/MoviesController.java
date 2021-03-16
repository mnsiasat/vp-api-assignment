package com.vp.api.controller;

import com.vp.api.model.Movie;
import com.vp.api.service.MoviesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/movies")
public class MoviesController {

    private Logger logger = LoggerFactory.getLogger(MoviesController.class);

    private final MoviesService service;

    public MoviesController(MoviesService moviesService) {
        this.service = moviesService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity getMovies() {
        ResponseEntity responseEntity = null;
        try {
            final List<Movie> movies = service.getAllMovies();
            responseEntity = new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Failed to retrieve movies: ",e);
            responseEntity = new ResponseEntity("Failed to retrieve movies", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity addMovie(@RequestBody Movie newMovie) {
        ResponseEntity responseEntity = null;

        try {
            newMovie.setWatched(false);
            service.addMovie(newMovie);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            logger.error("Failed to add movie: ",e);
            responseEntity = new ResponseEntity("Failed to add movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity updateMovie(@RequestBody Movie updateRequest, @PathVariable String id) {
        ResponseEntity responseEntity = null;

        try {
            final Movie updatedMovie = service.updateMovie(id,updateRequest);
            if(updatedMovie == null){
                responseEntity = new ResponseEntity("Failed to update Movie. Please check if id is correct.", HttpStatus.BAD_REQUEST);
            } else {
                responseEntity = new ResponseEntity<Movie>(updatedMovie,HttpStatus.OK);
            }

        } catch (Exception e) {
            logger.error("Failed to add movie: ",e);
            responseEntity = new ResponseEntity("Failed to update movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteMovie(@PathVariable String id) {
        ResponseEntity responseEntity = null;

        try {
            service.deleteMovie(id);
            responseEntity = ResponseEntity.status(HttpStatus.OK).build();

        } catch (Exception e) {
            logger.error("Failed to delete movie: ",e);
            responseEntity = new ResponseEntity("Failed to delete movie", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/movies/watched")
    public ResponseEntity getWatchedMovies() {
        ResponseEntity responseEntity = null;
        try {
            final List<Movie> movies = service.getWatchedMovies();
            responseEntity = new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Failed to retrieve movies: ",e);
            responseEntity = new ResponseEntity("Failed to retrieve movies", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * TODO:
     *  POST /movies
     *  GET /movies all movies
     *  PUT /movies/{id} Mark movie as watched
     *  DELETE /movies/{id} Remove movie
     *  GET /movies/watched Watched movies
     * */
}
