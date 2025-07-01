package com.movie.reservation.controller;



import com.movie.reservation.dto.MovieRequest;
import com.movie.reservation.dto.MovieResponse;
import com.movie.reservation.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> addMovie(@RequestBody MovieRequest request) {
        return new ResponseEntity<>(movieService.createMovie(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable UUID id, @RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPoster(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(movieService.uploadPoster(file));
    }
}