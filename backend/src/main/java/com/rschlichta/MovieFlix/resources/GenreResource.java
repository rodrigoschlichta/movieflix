package com.rschlichta.MovieFlix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rschlichta.MovieFlix.entities.Genre;
import com.rschlichta.MovieFlix.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {
	
	@Autowired
	private GenreService service;
	
	@GetMapping
	public ResponseEntity<List<Genre>> findall(){
		List<Genre> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
