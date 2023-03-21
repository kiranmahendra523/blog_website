package com.saikiran.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saikiran.blog.entities.Comment;
import com.saikiran.blog.entities.Post;
import com.saikiran.blog.exception.ResourceNotFoundException;
import com.saikiran.blog.payloads.CommentDto;
import com.saikiran.blog.repository.CommentRepo;
import com.saikiran.blog.repository.PostRepo;
import com.saikiran.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postid",postId));
		
		Comment comment = this.modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		
		Comment saveCmt = this.commentRepo.save(comment);
		
		return this.modelMapper.map(saveCmt, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
		this.commentRepo.delete(comment);

	}

}
