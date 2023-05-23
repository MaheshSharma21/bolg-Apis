package com.bikkadit.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Comment;
import com.bikkadit.blog.entities.Post;

import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.CommentDto;
import com.bikkadit.blog.repositories.CommentRepo;
import com.bikkadit.blog.repositories.PostRepo;
import com.bikkadit.blog.services.CommentServiceI;

@Service
public class CommentServiceImpl implements CommentServiceI {

	private Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		logger.info(" Initiated Request  for creating comment ");
		Post posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.modelmapper.map(commentDto, Comment.class);

		comment.setPost(posts);

		Comment save = this.commentRepo.save(comment);
		logger.info(" completed Request  for creating comment ");
		return this.modelmapper.map(save, CommentDto.class);

	}

	@Override
	public void deleteComment(Integer commentId) {
		logger.info(" Initiated Request  for deleting comment with commentId :{}", commentId);
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		logger.info(" completed Request  for deleting comment with commentId :{}", commentId);
		this.commentRepo.delete(comment);
	}

}
