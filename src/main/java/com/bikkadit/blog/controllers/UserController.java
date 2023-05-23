package com.bikkadit.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.helpers.AppConstant;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.services.UserServicesI;

@RestController
@RequestMapping("/api")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServicesI userServicesI;

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to create User
	 * @param userdto
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto) {
		logger.info(" Initiated Request for creating user");
		UserDto dto = this.userServicesI.createUser(userdto);
		logger.info(" Completed Request  for creating user");
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to create users
	 * @param list
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<String> createMultiUsers(@Valid @RequestBody List<UserDto> list) {
		logger.info(" Initiated Request for creating Users");
		this.userServicesI.createMultiUsers(list);
		logger.info(" completed Request  for creating users ");
		return new ResponseEntity<String>(AppConstant.USER_SAVED, HttpStatus.OK);

	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get user by userId
	 * @param Id
	 * @return
	 */
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer Id) {
		logger.info(" Initiated Request  for getting user with userId :{}", Id);
		UserDto userById = this.userServicesI.getUserById(Id);
		logger.info(" Completed Request  for getting user with userId :{}", Id);
		return new ResponseEntity<UserDto>(userById, HttpStatus.FOUND);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to get All users
	 * @return
	 */
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		logger.info(" Initiated Request for getting Users");
		List<UserDto> allUsers = this.userServicesI.getAllUsers();
		logger.info(" completed Request  for getting users ");
		return ResponseEntity.ok(allUsers);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to update user by userId
	 * @param userdto
	 * @param userId
	 * @return
	 */
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUserbyId(@Valid @RequestBody UserDto userdto, @PathVariable Integer userId) {
		logger.info(" Initiated Request  for updating user with userId :{}", userId);
		UserDto updateUserbyId = this.userServicesI.updateUserbyId(userdto, userId);
		logger.info(" Completed Request  for updating user with userId :{}", userId);
		return ResponseEntity.ok(updateUserbyId);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This Api is used to delete user by userId
	 * @param Id
	 * @return
	 */

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUserbyId(@PathVariable("userId") Integer Id) {
		logger.info(" Initiated Request  for deleting user with userId :{}", Id);
		this.userServicesI.deleteUserbyId(Id);
		logger.info(" Completed Request  for deleting user with userId :{}", Id);
		return ResponseEntity.ok(AppConstant.USER_DELETE);
	}
}
