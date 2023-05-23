package com.bikkadit.blog.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.helpers.ApiResponse;
import com.bikkadit.blog.helpers.AppConstant;
import com.bikkadit.blog.payloads.CommentDto;
import com.bikkadit.blog.services.CommentServiceI;

@RestController
@RequestMapping("/api")
public class CommentController {

	private Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentServiceI commentServiceI;

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to create Comment
	 * @param commentdto
	 * @param postId
	 * @return
	 */
	@PostMapping("/comments/{postId}")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentdto, @PathVariable Integer postId) {
		logger.info(" Initiated Request  for creating comment ");
		CommentDto comment = this.commentServiceI.createComment(commentdto, postId);
		logger.info(" completed Request  for creating comment ");
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
	}

	/**
	 * @author  Mahesh Sharma
	 * @apiNote This Api is used to delete post by commentId
	 * @param commentId
	 * @return
	 */
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
		logger.info(" Initiated Request  for deleting comment with commentId :{}", commentId);
		this.commentServiceI.deleteComment(commentId);
		logger.info(" completed Request  for deleting comment with commentId :{}", commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.COMMENT_DELETE, true), HttpStatus.OK);
	}
}
