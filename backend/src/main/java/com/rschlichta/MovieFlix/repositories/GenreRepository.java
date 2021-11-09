package com.rschlichta.MovieFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rschlichta.MovieFlix.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
