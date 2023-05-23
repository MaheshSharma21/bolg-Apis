package com.bikkadit.blog.services;

import java.util.List;

import com.bikkadit.blog.payloads.UserDto;

public interface UserServicesI {

	UserDto registerNewUser(UserDto userdto);

	UserDto createUser(UserDto userdto);

	void createMultiUsers(List<UserDto> list);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	UserDto updateUserbyId(UserDto userdto, Integer userId);

	void deleteUserbyId(Integer userId);
}
