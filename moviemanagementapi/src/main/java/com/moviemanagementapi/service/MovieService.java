package com.moviemanagementapi.service;

import com.moviemanagementapi.dao.MovieDao;
import com.moviemanagementapi.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class MovieService {

    private MovieDao movieDao;

    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public Movie createMovie(Movie movie) {
        return movieDao.createMovie(movie);
    }

    public ResponseEntity<Movie> getMovieById(long id) {
       Optional<Movie> optionalMovie= movieDao.getMovieById(id);
       if (optionalMovie.isPresent()){
           return new ResponseEntity<>(optionalMovie.get(), HttpStatus.FOUND);
       }
       else {
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       }

    }

    public Page<Movie> getAllMovies(int page ,int size) {
        Pageable pageable= PageRequest.of(page,size);
        return movieDao.getAllMovies(pageable);


    }

    public ResponseEntity<String> updateMovieById(long id, Movie movie) {

       Optional<Movie> optionalMovie=  movieDao.getMovieById(id);
       if (optionalMovie.isPresent()){
           Movie movie1=optionalMovie.get();
           movie1.setDirector(movie.getDirector());
           movie1.setTitle(movie.getTitle());
           movie1.setRating(movie.getRating());
           movie1.setReleaseYear(movie.getReleaseYear());
           movie1.setGenre(movie.getGenre());

            movieDao.createMovie(movie1);
            return new ResponseEntity<>("Updated Sucessfully",HttpStatus.ACCEPTED);

       }
       else {
           return new ResponseEntity<>("Not Updated or  Id Not Found",HttpStatus.NOT_ACCEPTABLE);
       }

    }

    public ResponseEntity<String> deleteById(Long id) {
      if (  movieDao.deleteById(id)){
          return new  ResponseEntity<>(id +" Deleted Sucessfully ",HttpStatus.ACCEPTED);
      }
      else {
          return new ResponseEntity<>(id +"Id Not Found ",HttpStatus.NOT_FOUND);
      }
    }
}
