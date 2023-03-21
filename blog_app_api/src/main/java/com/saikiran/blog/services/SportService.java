package com.saikiran.blog.services;

import java.util.List;

import com.saikiran.blog.payloads.SportDto;

public interface SportService {
	SportDto createSport(SportDto sportDto);
	
	void deleteSport(Integer id);
	
	SportDto getSport(Integer id);
	
	List<SportDto> getAllSports();
}
