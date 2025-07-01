package com.movie.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequest {
    @Schema(description = "Title of the movie", example = "Inception")
    private String title;

    @Schema(description = "Description of the movie", example = "A mind-bending thriller")
    private String description;

    @Schema(description = "URL of the poster image", example = "/uploads/inception.jpg")
    private String posterUrl;

    @Schema(description = "UUID of the genre")
    private UUID genreId;
}
