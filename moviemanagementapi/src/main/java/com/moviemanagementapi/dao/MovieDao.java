package com.moviemanagementapi.dao;

import com.moviemanagementapi.model.Movie;
import com.moviemanagementapi.repo.MovieJpaRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public class MovieDao {
    private MovieJpaRepo movieJpaRepo;

    public MovieDao(MovieJpaRepo movieJpaRepo) {
        this.movieJpaRepo = movieJpaRepo;
    }


    public Movie createMovie(Movie movie) {
        return movieJpaRepo.save(movie);
    }



    public Optional<Movie> getMovieById(long id) {
        return movieJpaRepo.getMovieById(id);
    }


    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieJpaRepo.findAll(pageable);
    }

    public boolean deleteById(Long id) {
        Optional<Movie> optionalMovie=getMovieById(id);
       if (optionalMovie.isPresent()){
            movieJpaRepo.deleteById(id);
            return true;
       }
       else {
           return  false;
       }
    }
}

