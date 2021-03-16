package com.vp.api.repository;

import com.vp.api.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends MongoRepository<Movie, String> {
    List<Movie> findByWatched(boolean watched);
}
