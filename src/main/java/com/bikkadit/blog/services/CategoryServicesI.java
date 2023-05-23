package com.bikkadit.blog.services;

import java.util.List;

import com.bikkadit.blog.payloads.CategoryDto;

public interface CategoryServicesI {

	CategoryDto createcategory(CategoryDto cate);

	CategoryDto getcategory(Integer categoryId);

	CategoryDto updatecategory(CategoryDto cate, Integer categoryId);

	List<CategoryDto> getCategories();

	void deletecategory(Integer categoryId);
}
