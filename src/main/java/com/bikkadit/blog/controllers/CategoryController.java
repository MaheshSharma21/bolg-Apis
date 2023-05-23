package com.bikkadit.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.helpers.AppConstant;
import com.bikkadit.blog.payloads.CategoryDto;
import com.bikkadit.blog.services.CategoryServicesI;

@RestController
@RequestMapping("/api")
public class CategoryController {

	private Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryServicesI categoryServicesI;

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to create category
	 * @param cate
	 * @return
	 */
	@PostMapping("/category")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto cate) {
		logger.info(" Initiated Request for creating category");
		CategoryDto dto = this.categoryServicesI.createcategory(cate);
		logger.info(" completed Request for creating category");
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.CREATED);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get category using categoryId
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable Integer categoryId) {
		logger.info(" Initiated Request for getting category with categoryId :{}", categoryId);
		CategoryDto dto = this.categoryServicesI.getcategory(categoryId);
		logger.info(" completed Request for getting category with categoryId :{}", categoryId);
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.OK);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to update category using categoryId
	 * @param cate
	 * @param categoryId
	 * @return
	 */
	@PutMapping("categories/{categoryId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto cate,
			@PathVariable Integer categoryId) {
		logger.info(" Initiated Request for updating category with categoryId :{}", categoryId);
		CategoryDto dto = this.categoryServicesI.updatecategory(cate, categoryId);
		logger.info(" completed Request for updating category with categoryId :{}", categoryId);
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.OK);

	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get All categories
	 * @return
	 */
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDto>> getCategories() {
		logger.info(" Initiated Request for getting All categories");
		List<CategoryDto> list = this.categoryServicesI.getCategories();
		logger.info(" completed Request for getting All categories");
		return new ResponseEntity<List<CategoryDto>>(list, HttpStatus.OK);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to delete category using categoryId
	 * @param categoryId
	 * @return
	 */
	@DeleteMapping("categories/{categoryId}")
	public ResponseEntity<String> deletecategory(@PathVariable Integer categoryId) {
		logger.info(" Initiated Request for deleting category with categoryId :{}", categoryId);
		this.categoryServicesI.deletecategory(categoryId);
		logger.info(" completing Request for deleting category with categoryId :{}", categoryId);
		return new ResponseEntity<String>(AppConstant.CATOGORY_DELETE, HttpStatus.OK);
	}
}
