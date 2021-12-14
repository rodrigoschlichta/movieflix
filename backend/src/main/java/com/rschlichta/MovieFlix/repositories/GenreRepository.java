package com.rschlichta.MovieFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rschlichta.MovieFlix.entities.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	

}
