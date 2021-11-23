package com.rschlichta.MovieFlix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rschlichta.MovieFlix.dto.MovieDTO;
import com.rschlichta.MovieFlix.entities.Movie;
import com.rschlichta.MovieFlix.repositories.MovieRepository;
import com.rschlichta.MovieFlix.services.exceptions.EntityNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Transactional(readOnly = true)
	public List<MovieDTO> findall(){
		List<Movie> list = repository.findAll();	
		return list.stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
		return new MovieDTO(entity);
	}
}
