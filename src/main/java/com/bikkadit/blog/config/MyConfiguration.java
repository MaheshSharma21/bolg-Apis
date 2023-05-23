package com.bikkadit.blog.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bikkadit.blog.entities.Role;
import com.bikkadit.blog.helpers.AppConstant;
import com.bikkadit.blog.repositories.RoleRepo;

@Configuration
public class MyConfiguration implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("sam"));

		try {
			Role role = new Role();

			role.setRoleId(AppConstant.USER_ADMIN);
			role.setName("ROLE_ADMIN");

			Role role1 = new Role();

			role1.setRoleId(AppConstant.USER_NORMAL);
			role1.setName("ROLE_NORMAL");

			List<Role> list = List.of(role, role1);

			List<Role> list2 = this.roleRepo.saveAll(list);

			list2.forEach(l -> {
				System.out.println(l.getName());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
