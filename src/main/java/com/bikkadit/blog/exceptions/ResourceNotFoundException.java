package com.bikkadit.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private Integer fieldvalue;

	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldvalue) {
		super(String.format("%s not found with %s :%s ", resourceName, fieldName, fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}

}
