package com.movie.reservation.dto;

import com.movie.reservation.entity.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {
    @Schema(description = "UUID of the movie")
    private UUID id;

    @Schema(description = "Title of the movie")
    private String title;

    @Schema(description = "Description of the movie")
    private String description;

    @Schema(description = "URL of the poster image")
    private String posterUrl;

    @Schema(description = "Name of the genre")
    private String genre;

    public MovieResponse(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.posterUrl = movie.getPosterUrl();
        this.genre = movie.getGenre().getName();
    }
}