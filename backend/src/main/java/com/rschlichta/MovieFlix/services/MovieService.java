package com.rschlichta.MovieFlix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rschlichta.MovieFlix.entities.Movie;
import com.rschlichta.MovieFlix.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	public List<Movie> findall(){
		return repository.findAll();
	}

}
