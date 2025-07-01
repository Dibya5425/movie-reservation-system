package com.movie.reservation.repository;

import com.movie.reservation.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {}
