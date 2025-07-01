package com.movie.reservation.service.impl;

import com.movie.reservation.dto.MovieRequest;
import com.movie.reservation.dto.MovieResponse;
import com.movie.reservation.exception.ResourceNotFoundException;
import com.movie.reservation.entity.Genre;
import com.movie.reservation.entity.Movie;
import com.movie.reservation.repository.GenreRepository;
import com.movie.reservation.repository.MovieRepository;
import com.movie.reservation.service.FileUploadService;
import com.movie.reservation.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final FileUploadService fileUploadService;

    @Override
    public MovieResponse createMovie(MovieRequest request) {
        log.info("Creating movie with title: {}", request.getTitle());
        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> {
                    log.error("Genre not found for ID: {}", request.getGenreId());
                    return new ResourceNotFoundException("Genre not found");
                });

        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .posterUrl(request.getPosterUrl())
                .genre(genre)
                .build();

        Movie saved = movieRepository.save(movie);
        log.info("Movie created successfully: {}", saved);
        return new MovieResponse(saved);
    }

    @Override
    public MovieResponse updateMovie(UUID id, MovieRequest request) {
        log.info("Updating movie with ID: {}", id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Movie not found for ID: {}", id);
                    return new ResourceNotFoundException("Movie not found");
                });

        Genre genre = genreRepository.findById(request.getGenreId())
                .orElseThrow(() -> {
                    log.error("Genre not found for ID: {}", request.getGenreId());
                    return new ResourceNotFoundException("Genre not found");
                });

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setPosterUrl(request.getPosterUrl());
        movie.setGenre(genre);

        Movie updated = movieRepository.save(movie);
        log.info("Movie updated successfully: {}", updated);
        return new MovieResponse(updated);
    }

    @Override
    public void deleteMovie(UUID id) {
        log.info("Deleting movie with ID: {}", id);
        movieRepository.deleteById(id);
        log.info("Movie deleted with ID: {}", id);
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        log.info("Fetched {} movies", movies.size());
        return movies.stream()
                .map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public String uploadPoster(MultipartFile file) {
        log.info("Uploading poster file: {}", file.getOriginalFilename());
        String posterUrl = fileUploadService.uploadPoster(file);
        log.info("Poster uploaded to URL: {}", posterUrl);
        return posterUrl;
    }
}
