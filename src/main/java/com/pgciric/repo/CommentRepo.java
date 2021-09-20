package com.pgciric.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgciric.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
