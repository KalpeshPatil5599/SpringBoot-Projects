package com.moviemanagementapi.controller;

import com.moviemanagementapi.model.Movie;
import com.moviemanagementapi.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @PostMapping

    public Movie createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }

    @GetMapping
    public Page<Movie> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        return movieService.getAllMovies(page,size);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long id ){
        return movieService.getMovieById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateMovieById(@PathVariable long id , @RequestBody Movie movie){
        return movieService.updateMovieById(id,movie);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ){
        return movieService.deleteById(id);
    }


}
