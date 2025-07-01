package com.movie.reservation.service;
import com.movie.reservation.dto.MovieRequest;
import com.movie.reservation.dto.MovieResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface MovieService {
    MovieResponse createMovie(MovieRequest request);
    MovieResponse updateMovie(UUID id, MovieRequest request);
    void deleteMovie(UUID id);
    List<MovieResponse> getAllMovies();
    String uploadPoster(MultipartFile file);
}