package com.bikkadit.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Integer postId;

	@NotBlank
	@Size(min = 7, max = 15, message = "PostTitle must be minimun of 7 character and max of 15 character...###")
	private String title;

	@NotBlank
	@Size(min = 10, max = 25, message = "PostContant must be minimun of 10 character and max of 25 character...###")
	private String contant;

	@NotEmpty
	@Size(max = 20, message = "imageSize must be maximum of 20 MB ")
	private String imageName;

	private Date date;

	private CategoryDto cate;

	private UserDto user;

	private Set<CommentDto> comment = new HashSet<>();
}
