package com.bikkadit.blog.helpers;

import java.util.List;

import com.bikkadit.blog.payloads.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostResponse {

	private List<PostDto> content;
	private int pageSize;
	private int pageNumber;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;

}
