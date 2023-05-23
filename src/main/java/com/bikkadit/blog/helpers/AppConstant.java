package com.bikkadit.blog.helpers;

public class AppConstant {

	public static final String USER_SAVED = "All users saved successfully ";
	public static final String USER_DELETE = "User deleted successfully";
	public static final String POST_DELETE = "post deleted Successfully";
	public static final String CATOGORY_DELETE = "category deleted successfully";
	public static final String COMMENT_DELETE = "Comments deleted successfully";
	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "2";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIR = "desc";

	// security constants
	public static final String INVALID = "Invalid details .....";
	public static final String DETAILS = "Invalid username or password !!";

	public static final String KEY = "Authorization";
	public static final String START_WITH = "Bearer";
	public static final String UNABLE = "Unable to get Jwt token";
	public static final String EXPIRED = "JWT token has expired .... !!";

	public static final String INVALID_JWT = " Invalid JWt token  .....";
	
	public static final String ACCESS = "Access  denied .....!!";
	

	public static final Integer USER_NORMAL = 102;
	public static final Integer USER_ADMIN = 101;

	public static final String[] PUBLIC_URLS = { "/api/v1/auth/**", "/v3/api-docs", "/v2/api-docs",
			"/swagger-resources/**", "swagger-ui/**", "webjars/**" };

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	public static final long JWT_TOKEN_VALIDITY_MULTIPLY = 5 * 60 * 60 * 100;

	public static final String SECRET = "JWTTokenKey";

	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	//swagger constants
	
	public static final String COURSE ="Blogging Application  :: Backend Course ";

	public static final String DEVELOPED = "This code is developed by Mahesh Sharma";

	public static final String NAME = "Mahesh Sharma";
	public static final String WEBSITE="http://maheshsharma127201.com";
	
	public static final String EMAIL = "maheshsharma127201@gmail.com";
	public static final String LICENSE="License of APIS";
	public static final String LICENSE_URL ="Api license URL";
	public static final String SERVICE_TERMS="Terms of Services";
	public static final String VERSION="1.0";
	public static final String GLOBAL ="global";
	public static final String JWT="JWT";
	public static final String ACCESS_EVERTHING="accessEverything";
	public static final String HEADER="header";
	
}
