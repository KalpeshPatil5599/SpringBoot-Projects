package com.moviemanagementapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Title must contain only letters, numbers, and spaces")
    private String title;



    @Pattern(regexp="^[A-Za-z ]+$")
    private String director;

    @Min(value = 1900, message = "Year must not be less than 1900")
    @Max(value = 2099, message = "Year must not be greater than 2099")
    private int releaseYear;

    @Pattern(
            regexp = "^(Action|Comedy|Drama|Horror|Romance|Thriller|Sci-Fi|Fantasy|Crime)$",
            message = "Genre must be one of: Action, Comedy, Drama, Horror, Romance, Thriller, Sci-Fi, Fantasy"
    )
    private String genre;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must not be greater than 10")
    private int rating;

}
