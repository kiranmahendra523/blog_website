package com.saikiran.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saikiran.blog.entities.Category;
import com.saikiran.blog.entities.Post;
import com.saikiran.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
