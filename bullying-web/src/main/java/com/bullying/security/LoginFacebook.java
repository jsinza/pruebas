package com.bullying.security;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bullying.model.User;
import com.bullying.service.UserService;
import com.bullying.util.Validator;

@Service
public class LoginFacebook {

	private static final String ACCESO_PROHIBIDO = "Acceso prohibido";
	private static final String FACEBOOK_TOKEN = "Facebook-Token";
	private static final String URL_FACEBOOK_DATA = "https://graph.facebook.com/me?access_token={token}&fields=email,name";
	
	@Autowired
	UserService userService;

	Cookie getCookie(HttpServletRequest httpRequest) {
		Cookie cookie = null;
		Cookie[] cookies = httpRequest.getCookies();
		Validator.validateNullEmpty(cookies, ACCESO_PROHIBIDO);
		Optional<Cookie> optional =Arrays.stream(cookies).filter(c -> FACEBOOK_TOKEN.equals(c.getName())).findFirst();
		if(optional.isPresent()) {
		    cookie = optional.get();
		}
		return cookie;
	}

	User getUser(HttpServletRequest httpRequest) {
		Cookie cookie = getCookie(httpRequest);
		Validator.validateNullEmpty(cookie, ACCESO_PROHIBIDO);
		RestTemplate restTemplate = new RestTemplate();
	    User user = restTemplate.getForObject(URL_FACEBOOK_DATA, User.class,cookie.getValue());	    
		return userService.getUserSecurity(user);
	}
}
