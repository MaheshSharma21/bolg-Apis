package com.bikkadit.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.entities.Post;
import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.helpers.PostResponse;
import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.repositories.CategoryRepo;
import com.bikkadit.blog.repositories.PostRepo;
import com.bikkadit.blog.repositories.UserRepo;
import com.bikkadit.blog.services.PostServicesI;

@Service
public class PostServicesImpl implements PostServicesI {

	private Logger logger = LoggerFactory.getLogger(PostServicesImpl.class);

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private CategoryRepo categoryrepo;

	@Override
	public PostDto createPost(PostDto postdto, Integer userId, Integer categoryId) {
		logger.info(" Initiated Request  for creating post with userId :{}", userId, " And with categoryId :{}",
				categoryId);
		User user = this.userrepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Category category = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Post posts = this.modelmapper.map(postdto, Post.class);
		posts.setImageName("Mahesh.png");
		posts.setDate(new Date());
		posts.setUser(user);
		posts.setCate(category);

		Post posts2 = this.postRepo.save(posts);

		PostDto postDto2 = this.modelmapper.map(posts2, PostDto.class);
		logger.info(" Completed Request  for creating post  with userId :{}", userId, " And with categoryId :{}",
				categoryId);
		return postDto2;
	}

	@Override
	public List<PostDto> getAllPostByUserId(Integer userId) {
		logger.info(" Initiated Request  for getting All post with userId :{}", userId);
		User user = this.userrepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		List<Post> list = this.postRepo.findByUser(user);
		List<PostDto> list2 = list.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info(" Completed Request  for getting All post with userId :{}", userId);
		return list2;
	}

	@Override
	public List<PostDto> getAllPostBycategoryId(Integer categoryId) {
		logger.info(" Initiated Request  for getting All post with categoryId :{}", categoryId);
		Category cat = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> listpost = this.postRepo.findByCate(cat);
		List<PostDto> list3 = listpost.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info(" completed Request  for getting All post with categoryId :{}", categoryId);
		return list3;
	}

	@Override
	public PostDto getPostByid(Integer postId) {
		logger.info(" Initiated Request  for getting  post with postId :{}", postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		PostDto dto = this.modelmapper.map(post, PostDto.class);
		logger.info(" completed Request  for getting  post with postId :{}", postId);
		return dto;
	}

	@Override
	public List<PostDto> getAllPost() {
		logger.info(" Initiated Request  for getting All post ");
		List<Post> list = this.postRepo.findAll();
		List<PostDto> list2 = list.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info(" completed Request  for getting All post ");
		return list2;
	}

	@Override
	public PostDto updatePostById(PostDto postdto, Integer postId) {
		logger.info(" Initiated Request  for updating  post with postId :{}", postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", "postId", postId));

		post.setContant(postdto.getContant());
		post.setTitle(postdto.getTitle());
		post.setImageName(postdto.getImageName());

		Post save = this.postRepo.save(post);

		PostDto dto = this.modelmapper.map(save, PostDto.class);
		logger.info(" completed Request  for updating  post with postId :{}", postId);
		return dto;
	}

	@Override
	public void deletePost(Integer postId) {
		logger.info(" Initiated Request  for deleting  post with postId :{}", postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		logger.info(" completing Request  for deleting  post with postId :{}", postId);
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse postPagiSort(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		logger.info(" Initiated Request  for  pagination "); // ternary operator Sort
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable of = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> page = this.postRepo.findAll(of);
		List<Post> list = page.getContent();

		List<PostDto> dto = list.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(dto);
		postResponse.setPageNumber(page.getNumber());
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElements(page.getTotalElements());
		postResponse.setTotalPages(page.getTotalPages());
		postResponse.setLastPage(page.isLast());
		logger.info(" completing  Request  for   pagination ");
		return postResponse;
	}

	@Override
	public List<PostDto> postSearchbyTitle(String keyword) {
		logger.info(" Initiated Request  for Searching  post with keyword :{}", keyword);
		List<Post> list = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> collect = list.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info(" completed Request  for Searching  post with Keyword :{}", keyword);
		return collect;
	}

}
