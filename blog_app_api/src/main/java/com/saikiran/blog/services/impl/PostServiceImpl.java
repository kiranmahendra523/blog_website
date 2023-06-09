package com.saikiran.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.saikiran.blog.entities.Category;
import com.saikiran.blog.entities.Post;
import com.saikiran.blog.entities.User;
import com.saikiran.blog.exception.ResourceNotFoundException;
import com.saikiran.blog.payloads.PostDto;
import com.saikiran.blog.payloads.PostResponse;
import com.saikiran.blog.repository.CategoryRepo;
import com.saikiran.blog.repository.PostRepo;
import com.saikiran.blog.repository.UserRepo;
import com.saikiran.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","with User Id : ",userId));
		
		Category category  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","with category Id : ",categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","with post Id : ",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		this.postRepo.save(post);
		
		PostDto postDtos = this.modelMapper.map(post, PostDto.class); 
		
		return postDtos;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","with post Id : ",postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		
		
		Pageable p = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		
		Page<Post> pagePosts = this.postRepo.findAll(p);
		
		List<Post> posts = pagePosts.getContent();
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElemnets(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","with post Id : ",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category  = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","with category Id : ",categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(category);
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user  = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","with User Id : ",userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPostsByTitle(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		return posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
