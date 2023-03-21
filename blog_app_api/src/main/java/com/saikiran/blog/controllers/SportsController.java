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
import com.saikiran.blog.payloads.SportDto;
import com.saikiran.blog.services.SportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sports")
public class SportsController {

	@Autowired
	private SportService sportservice;
	
	@PostMapping("/")
	public ResponseEntity<SportDto> createSport(@Valid @RequestBody  SportDto sportDto){
		
		SportDto sportDtos = this.sportservice.createSport(sportDto);	
		return new ResponseEntity<SportDto>(sportDtos,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/{sportId}")
	public ApiResponse deleteSport(@Valid @PathVariable Integer sportId){
		this.sportservice.deleteSport(sportId);
		return new ApiResponse("Sport Successfully deleted",true);
	}
	
	@GetMapping("/getsport/{sportId}")
	public ResponseEntity<SportDto> getSport(@Valid @RequestBody SportDto sportDto,@PathVariable Integer sportId){
		
		SportDto sportDtos = this.sportservice.getSport(sportId);
		
		return new ResponseEntity<SportDto>(sportDtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/getallsports/")
	public ResponseEntity<List<SportDto>> getAllSports(){
		
		List<SportDto> sportDtos = this.sportservice.getAllSports();
		
		return new ResponseEntity<List<SportDto>>(sportDtos,HttpStatus.OK);
	}
	
	
	
}
