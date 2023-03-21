package com.saikiran.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saikiran.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	Optional<User> findByEmail(String email);
	
}
