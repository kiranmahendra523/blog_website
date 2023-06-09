package com.saikiran.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.saikiran.blog.entities.Category;
import com.saikiran.blog.entities.Comment;
import com.saikiran.blog.entities.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	@NotEmpty
	@Size(min=5,max = 25,message="title must be minimum of 5 characters and max of 25 ")
	private String title;
	
	@NotEmpty
	@Size(min=20,max=100,message="content must be minimum of 20 characters and max of 100")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
