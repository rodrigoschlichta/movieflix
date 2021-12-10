package com.rschlichta.MovieFlix.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOG = LoggerFactory.getLogger(ReviewService.class);
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	
	public List<Review> findAll(){
		return repository.findAll();
	}
	
	@Transactional
	  public ReviewDTO insertReview(ReviewDTO dto) {
	    LOG.info("method=insertReview, msg=insert review {} to a movie", dto);
	    User user = authService.authenticated();
	    authService.validateSelfOrAdminAndMember(user.getId());

	    Optional<Movie> obj = movieRepository.findById(dto.getMovieId());
	    Movie movie = obj.orElseThrow(() -> {
	      LOG.info("method=insertReview, msg=movie id {} not found", dto.getMovieId());
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
