package com.bikkadit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Category;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.payloads.CategoryDto;
import com.bikkadit.blog.repositories.CategoryRepo;
import com.bikkadit.blog.services.CategoryServicesI;

@Service
public class CategoryServicesImpl implements CategoryServicesI {

	private Logger logger = LoggerFactory.getLogger(CategoryServicesImpl.class);

	@Autowired
	private CategoryRepo caterepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createcategory(CategoryDto cate) {
		logger.info(" Initiated Request for creating category");
		Category category = this.modelmapper.map(cate, Category.class);

		Category save = this.caterepo.save(category);
		logger.info(" completed Request for creating category");
		return this.modelmapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto getcategory(Integer categoryId) {
		logger.info(" Initiated Request for getting category with categoryId :{}", categoryId);
		Category category = this.caterepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		logger.info(" completed Request for getting category with categoryId :{}", categoryId);
		return this.modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto updatecategory(CategoryDto cate, Integer categoryId) {
		logger.info(" Initiated Request for updating category with categoryId :{}", categoryId);
		Category category = this.caterepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		category.setCategoryTitle(cate.getCategoryTitle());
		category.setCategoryDescription(cate.getCategoryDescription());

		Category save = this.caterepo.save(category);
		logger.info(" completed Request for updating category with categoryId :{}", categoryId);
		return this.modelmapper.map(save, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		logger.info(" Initiated Request for getting All categories");
		List<Category> list = this.caterepo.findAll();
		List<CategoryDto> list2 = list.stream().map((data) -> this.modelmapper.map(data, CategoryDto.class))
				.collect(Collectors.toList());
		logger.info(" completed Request for getting All categories");
		return list2;
	}

	@Override
	public void deletecategory(Integer categoryId) {
		logger.info(" Initiated Request for deleting category with categoryId :{}", categoryId);
		Category category = this.caterepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		logger.info(" completed Request for deleting category with categoryId :{}", categoryId);
		this.caterepo.delete(category);

	}

}
