package com.bikkadit.blog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bikkadit.blog.entities.User;
import com.bikkadit.blog.exceptions.ResourceNotFoundException;
import com.bikkadit.blog.repositories.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Initiated request for loading user by username ");
		// loading user from database by using username
		User user = this.userRepo.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email :" + username, 0));
		logger.info("Completed request for loading user by username ");
		return user;
	}

}
