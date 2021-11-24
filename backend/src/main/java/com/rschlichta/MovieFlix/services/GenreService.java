package com.rschlichta.MovieFlix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rschlichta.MovieFlix.dto.GenreDTO;
import com.rschlichta.MovieFlix.entities.Genre;
import com.rschlichta.MovieFlix.repositories.GenreRepository;
import com.rschlichta.MovieFlix.services.exceptions.EntityNotFoundException;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;
	
	@Transactional(readOnly = true)
	public Page<GenreDTO> findAllPaged(PageRequest pageRequest) {
		Page<Genre> list = repository.findAll(pageRequest);
		
		return list.map(x -> new GenreDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public GenreDTO findById(Long id) {
		Optional<Genre> obj = repository.findById(id);
		Genre entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
		return new GenreDTO(entity);
	}

}
