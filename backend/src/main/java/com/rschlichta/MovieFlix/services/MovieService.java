package com.rschlichta.MovieFlix.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rschlichta.MovieFlix.dto.MovieDTO;
import com.rschlichta.MovieFlix.dto.ReviewDTO;
import com.rschlichta.MovieFlix.entities.Genre;
import com.rschlichta.MovieFlix.entities.Movie;
import com.rschlichta.MovieFlix.entities.Review;
import com.rschlichta.MovieFlix.repositories.GenreRepository;
import com.rschlichta.MovieFlix.repositories.MovieRepository;
import com.rschlichta.MovieFlix.repositories.ReviewRepository;
import com.rschlichta.MovieFlix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllPaged(PageRequest pageRequest){
		Page<Movie> list = repository.findAll(pageRequest);	
		return list.map(x -> new MovieDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
		return new MovieDTO(entity);
	}
	
	@Transactional
	public MovieDTO insert(MovieDTO dto) {
		Movie movie = new Movie();

		copyDtoToEntity(dto, movie);
		movie = repository.save(movie);

		return new MovieDTO(movie);
	}
		
	@Transactional
	public MovieDTO update(MovieDTO dto, Long id) {
		try {
			Movie movie = repository.getOne(id);
			copyDtoToEntity(dto, movie);
			movie = repository.save(movie);
			return new MovieDTO(movie);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não Encontrado");
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		}
	}
	
	private void copyDtoToEntity(MovieDTO dto, Movie entity) {
		entity.setTitle(dto.getTitle());
		entity.setSubTitle(dto.getSubTitle());
		entity.setYear(dto.getYear());
		entity.setImgUrl(dto.getImgUrl());
		entity.setSynopsis(dto.getSynopsis());
		
		Genre genre = genreRepository.getOne(dto.getGenreId());
		entity.setGenre(genre);
		
		entity.getReviews().clear();
		
		for (ReviewDTO reviewDTO : dto.getReviews()) {
			Review review = reviewRepository.getOne(reviewDTO.getId());
			entity.getReviews().add(review);
		}
	}

}
	
	

