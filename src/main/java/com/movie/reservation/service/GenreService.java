package com.movie.reservation.service;

import com.movie.reservation.entity.Genre;

import java.util.List;
import java.util.UUID;

public interface GenreService {
    Genre createGenre(Genre genre);
    List<Genre> getAllGenres();
    void deleteGenre(UUID id);
}
