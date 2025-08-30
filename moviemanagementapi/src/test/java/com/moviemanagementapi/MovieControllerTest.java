package com.moviemanagementapi;

import com.moviemanagementapi.controller.MovieController;
import com.moviemanagementapi.model.Movie;
import com.moviemanagementapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");
        when(movieService.createMovie(any(Movie.class))).thenReturn(movie);
    mockMvc.perform(post("/movies")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\":\"Test Movie\"}"))
        .andExpect(status().isOk());
    }

    @Test
    void testGetAllMovies() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");
        Page<Movie> page = new PageImpl<>(Collections.singletonList(movie));
        when(movieService.getAllMovies(eq(0), eq(10))).thenReturn(page);
    mockMvc.perform(get("/movies?page=0&size=10"))
        .andExpect(status().isOk());
    }

    @Test
    void testGetMovieById() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");
        when(movieService.getMovieById(1L)).thenReturn(ResponseEntity.ok(movie));
    mockMvc.perform(get("/movies/1"))
        .andExpect(status().isOk());
    }

    @Test
    void testUpdateMovieById() throws Exception {
        when(movieService.updateMovieById(eq(1L), any(Movie.class))).thenReturn(ResponseEntity.ok("Updated"));
    mockMvc.perform(post("/movies/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\":\"Updated Movie\"}"))
        .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception {
        when(movieService.deleteById(1L)).thenReturn(ResponseEntity.ok("Deleted"));
    mockMvc.perform(delete("/movies/1"))
        .andExpect(status().isOk());
    }
}
