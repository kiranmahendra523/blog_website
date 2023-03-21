package com.saikiran.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saikiran.blog.entities.Post;
import com.saikiran.blog.entities.Sports;
import com.saikiran.blog.entities.User;
import com.saikiran.blog.exception.ResourceNotFoundException;
import com.saikiran.blog.payloads.SportDto;
import com.saikiran.blog.repository.SportsRepo;
import com.saikiran.blog.services.SportService;


@Service
public class SportServiceImpl implements SportService {
	
	
	@Autowired
	private SportsRepo sportsRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public SportDto createSport(SportDto sportDto) {
		
		Sports sport = this.modelMapper.map(sportDto,Sports.class);
		
		Sports savedSport = this.sportsRepo.save(sport);
		
		
		return this.modelMapper.map(savedSport,SportDto.class);

	}
	@Override
	public void deleteSport(Integer id) {
		Sports sports = this.sportsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sport","with sport Id : ",id));
		this.sportsRepo.delete(sports);
		
	}
	@Override
	public SportDto getSport(Integer id) {
		Sports sports = this.sportsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Sport","with sport Id : ",id));
		return this.modelMapper.map(sports,SportDto.class);
	}
	@Override
	public List<SportDto> getAllSports() {
		List<Sports> sports = this.sportsRepo.findAll();
		return sports.stream().map(user->this.modelMapper.map(user,SportDto.class)).collect(Collectors.toList());
	}

}
