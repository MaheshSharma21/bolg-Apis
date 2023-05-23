package com.bikkadit.blog.helpers;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;

	private String password;
}
