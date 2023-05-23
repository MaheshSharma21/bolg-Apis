package com.bikkadit.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min = 5, max = 15, message = "title must be within limit (min =5,max=15) .....!! ")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, max = 30, message = "description must be within min length 10 and max length 30")
	private String categoryDescription;
}
