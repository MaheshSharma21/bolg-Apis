package com.bikkadit.blog.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse {

	private String message;
	private boolean status;

}
