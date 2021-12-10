package com.rschlichta.MovieFlix.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rschlichta.MovieFlix.dto.ReviewDTO;
import com.rschlichta.MovieFlix.resources.exceptions.OAuthCustomError;
import com.rschlichta.MovieFlix.services.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/reviews")
@Api(tags = "Review Resource")
public class ReviewResource {
	
	@Autowired
	private ReviewService service;
	
	@ApiOperation(value = "View all Genres")
	@ApiResponses({ @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 401, message = "unauthorized", response = OAuthCustomError.class),
			@ApiResponse(code = 403, message = "forbidden", response = OAuthCustomError.class) })
	
	 @PostMapping
	  public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO dto) {
	    dto = service.insertReview(dto);
	    URI uri = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(dto.getId())
	            .toUri();

	    return ResponseEntity.created(uri).body(dto);
	  }
	}
