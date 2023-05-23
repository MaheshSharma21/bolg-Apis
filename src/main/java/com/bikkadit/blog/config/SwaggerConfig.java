package com.bikkadit.blog.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bikkadit.blog.helpers.AppConstant;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	// spring security jwt token
	public ApiKey apikeys() {

		return new ApiKey(AppConstant.JWT, AppConstant.AUTHORIZATION_HEADER, AppConstant.HEADER);
	}

	// spring security jwt token
	public List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}

	// spring security jwt token

	public List<SecurityReference> sf() {

		AuthorizationScope scope = new AuthorizationScope(AppConstant.GLOBAL, AppConstant.ACCESS_EVERTHING);
		return Arrays.asList(new SecurityReference(AppConstant.JWT, new AuthorizationScope[] { scope }));
	}

	// for swagger documentation
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apikeys())) // takes list of apikeys()
				.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	// swagger documentation
	private ApiInfo getInfo() {

		return new ApiInfo(AppConstant.COURSE, AppConstant.DEVELOPED, AppConstant.VERSION, AppConstant.SERVICE_TERMS,
				new Contact(AppConstant.NAME, AppConstant.WEBSITE, AppConstant.EMAIL), AppConstant.LICENSE,
				AppConstant.LICENSE_URL, Collections.emptyList());
	}
}
