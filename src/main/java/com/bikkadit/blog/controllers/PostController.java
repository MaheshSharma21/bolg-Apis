package com.bikkadit.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bikkadit.blog.helpers.AppConstant;
import com.bikkadit.blog.helpers.PostResponse;
import com.bikkadit.blog.payloads.PostDto;
import com.bikkadit.blog.services.FileServiceI;
import com.bikkadit.blog.services.PostServicesI;

@RestController
@RequestMapping("/api")
public class PostController {

	private Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostServicesI postServicesI;

	@Autowired
	private FileServiceI fileServiceI;

	@Value("${project.image}")
	private String path;

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to create post
	 * @param postdto
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	@PostMapping("/posts/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postdto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		logger.info(" Initiated Request  for creating post with userId :{}", userId, " And with the categoryId :{}",
				categoryId);
		PostDto postDto2 = this.postServicesI.createPost(postdto, userId, categoryId);
		logger.info(" Completed Request  for creating post with userId :{}", userId, " And with the categoryId :{}",
				categoryId);
		return new ResponseEntity<PostDto>(postDto2, HttpStatus.CREATED);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get All posts using userId
	 * @param userId
	 * @return
	 */
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<PostDto>> getAllPostByUserId(@PathVariable Integer userId) {
		logger.info(" Initiated Request  for getting All post with userId :{}", userId);
		List<PostDto> allPostByUserId = this.postServicesI.getAllPostByUserId(userId);
		logger.info(" Completed Request  for getting All post with userId :{}", userId);
		return new ResponseEntity<List<PostDto>>(allPostByUserId, HttpStatus.FOUND);

	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get All posts using categoryId
	 * @param categoryid
	 * @return
	 */

	@GetMapping("/posts/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getAllPostBycategoryId(@PathVariable Integer categoryId) {
		logger.info(" Initiated Request  for getting All post with categoryId :{}", categoryId);
		List<PostDto> allpostbycategoryId = this.postServicesI.getAllPostBycategoryId(categoryId);
		logger.info(" completed Request  for getting All post with categoryId :{}", categoryId);
		return new ResponseEntity<List<PostDto>>(allpostbycategoryId, HttpStatus.FOUND);

	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get post by postId
	 * @param postId
	 * @return
	 */
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostByid(@PathVariable Integer postId) {
		logger.info(" Initiated Request  for getting  post with postId :{}", postId);
		PostDto byid = this.postServicesI.getPostByid(postId);
		logger.info(" completed Request  for getting  post with postId :{}", postId);
		return new ResponseEntity<PostDto>(byid, HttpStatus.FOUND);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get All posts
	 * @return
	 */
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost() {
		logger.info(" Initiated Request  for getting All post ");
		List<PostDto> allPost = this.postServicesI.getAllPost();
		logger.info(" completed Request  for getting All post ");
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.FOUND);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to update post by using postId
	 * @param postdto
	 * @param postId
	 * @return
	 */
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postdto, @PathVariable Integer postId) {
		logger.info(" Initiated Request  for updating  post with postId :{}", postId);
		PostDto updatePostById = this.postServicesI.updatePostById(postdto, postId);
		logger.info(" completed Request  for updating  post with postId :{}", postId);
		return new ResponseEntity<PostDto>(updatePostById, HttpStatus.FOUND);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to delete post by postId
	 * @param postId
	 * @return
	 */
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
		logger.info(" Initiated Request  for deleting  post with postId :{}", postId);
		this.postServicesI.deletePost(postId);
		logger.info(" completing Request  for deleting  post with postId :{}", postId);
		return new ResponseEntity<String>(AppConstant.POST_DELETE, HttpStatus.OK);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to perform Pagination
	 * @param pageNumber
	 * @param pageSize
	 * @param sortBy
	 * @param sortDir
	 * @return
	 */

	@GetMapping("/posts/pagination")
	public ResponseEntity<PostResponse> postPagiSort(

			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,

			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,

			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,

			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
		logger.info(" Initiated Request  for  pagination ");
		PostResponse pg = this.postServicesI.postPagiSort(pageNumber, pageSize, sortBy, sortDir);
		logger.info(" completing  Request  for   pagination ");
		return new ResponseEntity<PostResponse>(pg, HttpStatus.OK);

	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to search post by using title
	 * @param keyword
	 * @return
	 */

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> postSearchbyTitle(@PathVariable String keyword) {
		logger.info(" Initiated Request  for Searching  post with keyword :{}", keyword);
		List<PostDto> title = this.postServicesI.postSearchbyTitle(keyword);
		logger.info(" Initiated Request  for Searching  post with Keyword :{}", keyword);
		return new ResponseEntity<List<PostDto>>(title, HttpStatus.OK);

	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to upload Image
	 * @param image
	 * @param postId
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/posts/image/upload/{postId}")
	
	public ResponseEntity<PostDto> uploadPostImage(@RequestPart MultipartFile image, @PathVariable Integer postId)
			throws IOException {
		logger.info(" Initiated Request  for uploading Image   with postId :{}", postId);
		PostDto postDto = this.postServicesI.getPostByid(postId);
		String fileName = this.fileServiceI.uploadImage(path, image);

		postDto.setImageName(fileName);
		PostDto updatePostById = this.postServicesI.updatePostById(postDto, postId);
		logger.info(" completed Request  for uploading Image  with postId :{}", postId);
		return new ResponseEntity<PostDto>(updatePostById, HttpStatus.OK);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to excess/download Image
	 * @param imageName
	 * @param response
	 * @throws IOException
	 */

	@GetMapping(value = "posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		logger.info(" Initiated Request  for accessing Image ");
		InputStream resource = this.fileServiceI.getresource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		logger.info(" completed Request  for accessing Image ");
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
