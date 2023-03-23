package com.saikiran.blog.services;

import java.util.List;

import com.saikiran.blog.payloads.GroundDto;

public interface GroundService {

	 GroundDto createGround(GroundDto groundDto);
	
	 void deleteGround(Integer groundId);
	 
	 GroundDto updateGround(GroundDto groundDto,Integer gorundId);
	 
	 GroundDto getSingleGround(Integer groundId);
	 
	 List<GroundDto> getAllGrounds();
 
}
