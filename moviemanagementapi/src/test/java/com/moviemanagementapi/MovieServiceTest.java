package com.moviemanagementapi;

import com.moviemanagementapi.model.Movie;
import com.moviemanagementapi.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    @Mock
    private com.moviemanagementapi.dao.MovieDao movieDao;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        when(movieDao.createMovie(any(Movie.class))).thenReturn(movie);
        Movie result = movieService.createMovie(movie);
        assertNotNull(result);
        assertEquals("Test Movie", result.getTitle());
    }

    @Test
    void testGetMovieById_found() {
        Movie movie = new Movie();
        movie.setId(1L);
        when(movieDao.getMovieById(1L)).thenReturn(java.util.Optional.of(movie));
        ResponseEntity<Movie> response = movieService.getMovieById(1L);
    assertEquals(302, response.getStatusCode().value()); // HttpStatus.FOUND
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetMovieById_notFound() {
        when(movieDao.getMovieById(2L)).thenReturn(java.util.Optional.empty());
        ResponseEntity<Movie> response = movieService.getMovieById(2L);
    assertEquals(404, response.getStatusCode().value()); // HttpStatus.NOT_FOUND
        assertNull(response.getBody());
    }

    @Test
    void testGetAllMovies() {
        Movie movie = new Movie();
        Page<Movie> page = new PageImpl<>(Collections.singletonList(movie));
        when(movieDao.getAllMovies(any(org.springframework.data.domain.Pageable.class))).thenReturn(page);
        Page<Movie> result = movieService.getAllMovies(0, 10);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testUpdateMovieById_found() {
        Movie movie = new Movie();
        movie.setId(1L);
        when(movieDao.getMovieById(1L)).thenReturn(java.util.Optional.of(movie));
        when(movieDao.createMovie(any(Movie.class))).thenReturn(movie);
        ResponseEntity<String> response = movieService.updateMovieById(1L, movie);
    assertEquals(202, response.getStatusCode().value()); // HttpStatus.ACCEPTED
        assertEquals("Updated Sucessfully", response.getBody());
    }

    @Test
    void testUpdateMovieById_notFound() {
        Movie movie = new Movie();
        when(movieDao.getMovieById(2L)).thenReturn(java.util.Optional.empty());
        ResponseEntity<String> response = movieService.updateMovieById(2L, movie);
    assertEquals(406, response.getStatusCode().value()); // HttpStatus.NOT_ACCEPTABLE
        assertEquals("Not Updated or  Id Not Found", response.getBody());
    }

    @Test
    void testDeleteById_found() {
        when(movieDao.deleteById(1L)).thenReturn(true);
        ResponseEntity<String> response = movieService.deleteById(1L);
    assertEquals(202, response.getStatusCode().value()); // HttpStatus.ACCEPTED
        assertTrue(response.getBody().contains("Deleted Sucessfully"));
    }

    @Test
    void testDeleteById_notFound() {
        when(movieDao.deleteById(2L)).thenReturn(false);
        ResponseEntity<String> response = movieService.deleteById(2L);
    assertEquals(404, response.getStatusCode().value()); // HttpStatus.NOT_FOUND
        assertTrue(response.getBody().contains("Id Not Found"));
    }
}
