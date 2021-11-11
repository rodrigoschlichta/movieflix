package com.rschlichta.MovieFlix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rschlichta.MovieFlix.entities.Genre;
import com.rschlichta.MovieFlix.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;

	public List<Genre> findAll() {
		return repository.findAll();
	}

}
