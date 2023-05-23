package com.bikkadit.blog.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bikkadit.blog.entities.Role;
import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.helpers.AppConstant;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.repositories.RoleRepo;
import com.bikkadit.blog.repositories.UserRepo;
import com.bikkadit.blog.services.UserServicesI;

@Service
public class UserServicesImpl implements UserServicesI {

	private Logger logger = LoggerFactory.getLogger(UserServicesImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userdto) {
		logger.info(" Initiated Request for creating user");
		User user = this.modelMapper.map(userdto, User.class);

		user.setDate(LocalDateTime.now());
		User user2 = this.userRepo.save(user);
		logger.info(" Completed Request for creating user");
		return this.modelMapper.map(user2, UserDto.class);
	}

	@Override
	public void createMultiUsers(List<UserDto> list) {
		logger.info(" Initiated Request for creating Users");
		List<User> users = list.stream().map(list1 -> this.modelMapper.map(list1, User.class))
				.collect(Collectors.toList());

		 List<User> saveAll = this.userRepo.saveAll(users);
		logger.info(" completed Request  for creating users ");

	}

	@Override
	public UserDto getUserById(Integer userId) {
		logger.info(" Initiated Request  for getting user with userId :{}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		logger.info(" Completed Request  for getting user with userId :{}", userId);
		return this.modelMapper.map(user, UserDto.class);

	}

	@Override
	public List<UserDto> getAllUsers() {
		logger.info(" Initiated Request for getting Users");
		List<User> userlist = this.userRepo.findAll();

		List<UserDto> list2 = userlist.stream().map((data) -> this.modelMapper.map(data, UserDto.class))
				.collect(Collectors.toList());
		logger.info(" completed Request  for getting users ");
		return list2;
	}

	@Override
	public UserDto updateUserbyId(UserDto userdto, Integer userId) {
		logger.info(" Initiated Request  for updating user with userId :{}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		user.setUserName(userdto.getUserName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());

		User user2 = this.userRepo.save(user);
		UserDto userDto = this.modelMapper.map(user2, UserDto.class);
		logger.info(" Completed Request  for updating user with userId :{}", userId);
		return userDto;
	}

	@Override
	public void deleteUserbyId(Integer userId) {
		logger.info(" Initiated Request  for deleting user with userId :{}", userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		this.userRepo.delete(user);
		logger.info(" Completed Request  for deleting user with userId :{}", userId);
	}

	@Override
	public UserDto registerNewUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto, User.class);

		// password incode
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// getting role
		Role role = this.roleRepo.findById(AppConstant.USER_NORMAL).get();

		user.getRoles().add(role);
		User user2 = this.userRepo.save(user);
		return this.modelMapper.map(user2, UserDto.class);
	}

	// to convert userdto to user entity

	/*
	 * public User userdtoToUser(UserDto userdto) {
	 * 
	 * User user = new User();
	 * 
	 * user.setUserName(userdto.getUserName()); user.setEmail(userdto.getEmail());
	 * user.setPassword(userdto.getPassword()); user.setAbout(userdto.getAbout());
	 * 
	 * return user; }
	 */

	// to convert user entity to userdto

	/*
	 * public UserDto userTouserdto(User user) {
	 * 
	 * UserDto userdto = new UserDto(); userdto.setUserId(user.getUserId());
	 * 
	 * userdto.setUserName(user.getUserName()); userdto.setEmail(user.getEmail());
	 * userdto.setPassword(user.getPassword()); userdto.setAbout(user.getAbout());
	 * 
	 * return userdto; }
	 */

}
