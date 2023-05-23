package com.bikkadit.blog.services;

import com.bikkadit.blog.payloads.CommentDto;

public interface CommentServiceI {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);
}
