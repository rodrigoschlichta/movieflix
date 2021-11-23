package com.rschlichta.MovieFlix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rschlichta.MovieFlix.entities.Review;
import com.rschlichta.MovieFlix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	public List<Review> findAll(){
		return repository.findAll();
	}

}
