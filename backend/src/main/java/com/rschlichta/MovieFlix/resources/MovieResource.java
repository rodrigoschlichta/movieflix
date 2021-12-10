package com.rschlichta.MovieFlix.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rschlichta.MovieFlix.dto.MovieDTO;
import com.rschlichta.MovieFlix.resources.exceptions.OAuthCustomError;
import com.rschlichta.MovieFlix.services.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/movies")
@Api(tags  = "Movie Resource")
public class MovieResource {
	
	@Autowired
	private MovieService service;
	
	@ApiOperation(value = "View all Genres")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 401, message = "unauthorized", response = OAuthCustomError.class),
			@ApiResponse(code = 403, message = "forbidden", response = OAuthCustomError.class) })
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAll(
			
			@RequestParam (value = "genreId", defaultValue = "0") Long genreId,
			@RequestParam (value = "page", defaultValue = "0") Integer page,
			@RequestParam (value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam (value = "orderBy", defaultValue = "title") String orderBy,
			@RequestParam (value = "direction", defaultValue = "ASC") String direction
			
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<MovieDTO> list = service.findAllPaged(genreId, pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<MovieDTO> insert(@Valid @RequestBody MovieDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO dto , @PathVariable Long id){
		dto = service.update(dto, id);
		return ResponseEntity.ok().body(dto);
	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
