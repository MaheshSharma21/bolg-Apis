package com.bikkadit.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.bikkadit.blog.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Integer userId;

	@NotEmpty
	@Size(min = 4, max = 10, message = "userName must be minimun of 4 character and max of 10 character...###")
	private String userName;

	@Email(message = "Please enter Valid Email id ...!!")
	private String email;

	@NotEmpty
	@Pattern(regexp = "^[a-s]{3}", message = "Password Incorrect ....Must be of  length =3")
	private String password;

	@NotEmpty
	@Size(min = 8, max = 20, message = " *** about User min length must be 8 and max length must be 20 ***")
	private String about;

	private Set<Role> roles = new HashSet<>();
}
