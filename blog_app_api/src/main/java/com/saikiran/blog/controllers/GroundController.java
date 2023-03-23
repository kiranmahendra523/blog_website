package com.saikiran.blog.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saikiran.blog.payloads.ApiResponse;
import com.saikiran.blog.payloads.GroundDto;
import com.saikiran.blog.services.GroundService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/grounds/")
public class GroundController {

	@Autowired
	private GroundService groundService;
	
	
	@PostMapping("/grounds/createground/")
	public ResponseEntity<GroundDto> createPost(@Valid @RequestBody GroundDto groundDto){
		
		GroundDto newGroundDto = this.groundService.createGround(groundDto);
		
		return new ResponseEntity<GroundDto>(newGroundDto,HttpStatus.CREATED);

	}
	
	@DeleteMapping("/grounds/deleteGround/")
	public ApiResponse deleteGround(@Valid @PathVariable Integer groundId) {
		this.groundService.deleteGround(groundId);
		return new ApiResponse("Successfully Deleted the Ground ",true);
		
	}
	
	@GetMapping("/grounds/{groundId}")
	public ResponseEntity<GroundDto> getSingleGround(@Valid @PathVariable Integer groundId){
		GroundDto groundDto = this.groundService.getSingleGround(groundId);
		return new ResponseEntity<GroundDto>(groundDto,HttpStatus.OK);
	}
	
	@GetMapping("/grounds/getallgrounds/")
	public ResponseEntity<List<GroundDto>> getAllGrounds(){
		
		List<GroundDto> groundDtos =  this.groundService.getAllGrounds();
		
		return new ResponseEntity<List<GroundDto>>(groundDtos,HttpStatus.OK); 
		
	}
	
}
