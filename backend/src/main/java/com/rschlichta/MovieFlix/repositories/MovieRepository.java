package com.rschlichta.MovieFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rschlichta.MovieFlix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
