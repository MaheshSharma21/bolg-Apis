package com.bikkadit.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bikkadit.blog.helpers.AppConstant;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("Initiated request for token validation and Authentication ");
		// get Token

		String requestToken = request.getHeader(AppConstant.KEY);

		// token exists in form of :Bearer 245436jjdwl

		String username = null;
		String token = null;

		if (requestToken != null && requestToken.startsWith(AppConstant.START_WITH)) {

			token = requestToken.substring(7);

			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				logger.error("Unauthorized error :{}", AppConstant.UNABLE);

			} catch (ExpiredJwtException j) {
				logger.error("Unauthorized error :{}", AppConstant.EXPIRED);

			} catch (MalformedJwtException m) {
				logger.error("Unauthorized error :{}", AppConstant.INVALID_JWT);

			}
		} else {
			logger.warn("JWT token does not starts with Bearer");

		}

		// once we get the token ,now we will perform validation

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.jwtTokenHelper.validateToken(token, userDetails)) {

				// perfectly working
				// now next step :Authentication

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				logger.info("Invalid JWt token  .....");

			}

		} else {
			logger.warn("username is null or  context is not null");

		}
		filterChain.doFilter(request, response);

		logger.info("completed request for token validation and Authentication ");
	}

}
