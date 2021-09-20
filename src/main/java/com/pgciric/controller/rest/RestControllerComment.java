package com.pgciric.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pgciric.entity.Blog;
import com.pgciric.entity.Comment;
import com.pgciric.service.BlogService;
import com.pgciric.service.CommentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RestControllerComment {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private BlogService blogService;
	
	@PostMapping({"/comment","/comment/"})
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @RequestParam("blogId") int blogId){
		
		Blog blog = blogService.getById(blogId);
		comment.setBlog(blog);
		
		commentService.saveComment(comment);
		
		return new ResponseEntity<Comment>(comment,HttpStatus.CREATED);
	}

}
