package com.rschlichta.MovieFlix.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rschlichta.MovieFlix.dto.GenreDTO;
import com.rschlichta.MovieFlix.resources.exceptions.OAuthCustomError;
import com.rschlichta.MovieFlix.services.GenreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/genres")
@Api(tags = "Genre Resource")
public class GenreResource {
	
	@Autowired
	private GenreService service;
	
	@ApiOperation(value = "View all Genres")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 401, message = "unauthorized", response = OAuthCustomError.class),
			@ApiResponse(code = 403, message = "forbidden", response = OAuthCustomError.class) })
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll(){
		
		List<GenreDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> findById(@PathVariable Long id){
		GenreDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<GenreDTO> insert(@RequestBody GenreDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				  .buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> update(@PathVariable Long id , @RequestBody GenreDTO dto){
		dto = service.update(id,dto);
		return ResponseEntity.ok().body(dto);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
