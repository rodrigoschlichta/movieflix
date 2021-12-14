package com.rschlichta.MovieFlix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.rschlichta.MovieFlix.entities.Review;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Campo Obrigatório")
	private String text;
	private Long movieId;
	
	public UserDTO user;
	
	public ReviewDTO() {		
	}

	public ReviewDTO(Long id, String text, UserDTO user, Long movieId) {
		this.id = id;
		this.text = text;
		this.user = user;
		this.movieId = movieId;
		
	}
	
	public ReviewDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		user = new UserDTO(entity.getUser());
		movieId = entity.getMovie().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	

}
