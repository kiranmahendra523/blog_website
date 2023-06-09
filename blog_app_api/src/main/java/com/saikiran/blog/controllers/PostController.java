package com.saikiran.blog.controllers;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saikiran.blog.entities.Post;
import com.saikiran.blog.payloads.ApiResponse;
import com.saikiran.blog.payloads.PostDto;
import com.saikiran.blog.payloads.PostResponse;
import com.saikiran.blog.repository.PostRepo;
import com.saikiran.blog.services.FileService;
import com.saikiran.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;


@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService; 
	
	@Autowired
	private  FileService fileService;
	

	@Value("${project.image}")
	private String path; 
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		
		
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@Valid @PathVariable Integer userId){
		
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@Valid @PathVariable Integer categoryId){
		
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(@Valid @RequestParam(value = "pageNumber",defaultValue = "0",required=false) Integer pageNumber ,@RequestParam(value="sortBy",defaultValue = "5",required = false) Integer pageSize,@RequestParam(value = "pageNumber",defaultValue = "postId",required=false) String sortBy ){
		PostResponse  posts = this.postService.getAllPost(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@Valid @PathVariable Integer postId){
		PostDto  postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@Valid @PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is sucessfully deleted",true);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatepost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searhPostsByTitle(@Valid @PathVariable String keyword){
		List<PostDto> postDto = this.postService.searchPostsByTitle(keyword);
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@Valid @RequestParam("image") MultipartFile image , @PathVariable Integer postId)throws IOException{
		
		PostDto postDto = this.postService.getPostById(postId);
		String fileName =  this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@Valid @PathVariable("imageName") String imagename,HttpServletResponse response)throws IOException{
		InputStream resource = this.fileService.getResource(path, imagename);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
 