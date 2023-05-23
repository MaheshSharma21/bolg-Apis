package com.bikkadit.blog.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadit.blog.exceptions.ApiException;
import com.bikkadit.blog.helpers.AppConstant;
import com.bikkadit.blog.helpers.JwtAuthRequest;
import com.bikkadit.blog.helpers.JwtAuthResponse;
import com.bikkadit.blog.payloads.UserDto;
import com.bikkadit.blog.security.JwtTokenHelper;
import com.bikkadit.blog.services.UserServicesI;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServicesI userServicesI;

	/**
	 * @author Mahesh Sharma
	 * @apiNote This is a login login Api
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		logger.info(" Initiated Request for  login ");
		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(token);
		logger.info(" Completed Request for login");
		return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This is a api related to authenticate
	 * @param username
	 * @param passsword
	 * @throws Exception
	 */

	private void authenticate(String username, String passsword) throws Exception {
		logger.info(" Initiated Request for Authenticate");
		UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, passsword);

		try {
			this.authenticationManager.authenticate(userPasswordAuthenticationToken);
		} catch (BadCredentialsException b) {
			System.out.println(AppConstant.INVALID);
			throw new ApiException(AppConstant.DETAILS);
		}
		logger.info(" Completed Request for Authenticate");
	}

	/**
	 * @author Mahesh Sharma
	 * @apiNote This is a Registration Api
	 * @param userDto
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) {
		logger.info(" Initiated Request for Registration");
		UserDto newUser = this.userServicesI.registerNewUser(userDto);
		logger.info(" Completed Request for Registration");
		return new ResponseEntity<UserDto>(newUser, HttpStatus.CREATED);
	}
}
