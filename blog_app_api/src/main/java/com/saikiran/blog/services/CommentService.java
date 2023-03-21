package com.saikiran.blog.services;


import com.saikiran.blog.payloads.CommentDto;



public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);
}
