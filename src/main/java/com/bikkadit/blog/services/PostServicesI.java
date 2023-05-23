package com.bikkadit.blog.services;

import java.util.List;

import com.bikkadit.blog.helpers.PostResponse;
import com.bikkadit.blog.payloads.PostDto;

public interface PostServicesI {

	PostDto createPost(PostDto postdto, Integer userId, Integer categoryId);

	List<PostDto> getAllPostByUserId(Integer userId);

	List<PostDto> getAllPostBycategoryId(Integer categoryId);

	PostDto getPostByid(Integer postId);

	List<PostDto> getAllPost();

	PostDto updatePostById(PostDto postdto, Integer postId);

	void deletePost(Integer postId);

	PostResponse postPagiSort(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	List<PostDto> postSearchbyTitle(String keyword);
}
