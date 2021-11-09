package com.rschlichta.MovieFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rschlichta.MovieFlix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
