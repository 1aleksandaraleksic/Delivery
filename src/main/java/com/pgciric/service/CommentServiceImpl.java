package com.pgciric.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgciric.entity.Comment;
import com.pgciric.repo.CommentRepo;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepo commentRepo;
	
	@Override
	public Comment saveComment(Comment comment) {

		return commentRepo.save(comment);
	}

}
