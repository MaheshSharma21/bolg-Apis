package com.bikkadit.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private Integer commentId;

	@NotBlank
	@Size(min = 10, max = 30, message = "content must be within limit (min =10,max=30) .....!! ")
	private String content;
}
