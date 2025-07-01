package com.movie.reservation.service.impl;

import com.movie.reservation.exception.ResourceNotFoundException;
import com.movie.reservation.entity.Genre;
import com.movie.reservation.repository.GenreRepository;
import com.movie.reservation.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre createGenre(Genre genre) {
        Genre saved = genreRepository.save(genre);
        log.info("Genre created: {}", saved);
        return saved;
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        log.info("Fetched {} genres", genres.size());
        return genres;
    }

    @Override
    public void deleteGenre(UUID id) {
        genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found"));
        genreRepository.deleteById(id);
        log.info("Genre deleted with id: {}", id);
    }
}
