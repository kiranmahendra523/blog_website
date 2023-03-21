package com.saikiran.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saikiran.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
	
}
