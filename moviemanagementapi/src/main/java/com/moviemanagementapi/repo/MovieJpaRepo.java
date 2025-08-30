package com.moviemanagementapi.repo;

import com.moviemanagementapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieJpaRepo extends JpaRepository<Movie,Long> {

    Optional<Movie> getMovieById(long id);
}
