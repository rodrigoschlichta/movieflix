package com.rschlichta.MovieFlix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rschlichta.MovieFlix.dto.ReviewDTO;
import com.rschlichta.MovieFlix.entities.Movie;
import com.rschlichta.MovieFlix.entities.Review;
import com.rschlichta.MovieFlix.entities.User;
import com.rschlichta.MovieFlix.repositories.MovieRepository;
import com.rschlichta.MovieFlix.repositories.ReviewRepository;
import com.rschlichta.MovieFlix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional
	  public ReviewDTO insertReview(ReviewDTO dto) {
	    User user = authService.authenticated();
	    authService.validateSelfOrAdminAndMember(user.getId());

	    Optional<Movie> obj = movieRepository.findById(dto.getMovieId());
	    Movie movie = obj.orElseThrow(() -> {
	    throw new ResourceNotFoundException("Movie not found: " + dto.getMovieId());
	    });

	    Review review = new Review();
	    review.setText(dto.getText());
	    review.setMovie(movie);
	    review.setUser(user);

	    review = repository.save(review);
	    return new ReviewDTO(review);
	  }
	}
