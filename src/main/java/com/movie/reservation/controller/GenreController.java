package com.movie.reservation.controller;

import com.movie.reservation.entity.Genre;
import com.movie.reservation.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
@Tag(name = "Genre Management")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.createGenre(genre), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
