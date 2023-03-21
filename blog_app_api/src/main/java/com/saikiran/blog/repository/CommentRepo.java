package com.saikiran.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saikiran.blog.entities.Comment;

public interface CommentRepo  extends JpaRepository<Comment,Integer>{

}
