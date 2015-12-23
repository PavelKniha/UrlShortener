package com.knihapaul.urlshortener.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knihapaul.urlshortener.exception.EmailAlreadyExistException;
import com.knihapaul.urlshortener.persistence.model.User;
import com.knihapaul.urlshortener.persistence.service.UserDto;
import com.knihapaul.urlshortener.persistence.service.UserService;
import com.knihapaul.urlshortener.web.util.GenericResponse;

@Controller
public class RegistrationController {

	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse registerUser(@Valid UserDto userDto) {
//		User registered = createNewUser(userDto);
		User registered = userService.registerNewUser(userDto);
//		if (registered == null) {
//			throw new EmailAlreadyExistException("User with email: "
//					+ userDto.getEmail() + " already exists");
//		}
		return new GenericResponse("success");
	}

//	private User createNewUser(UserDto userDto) {
//		User user = null;
//		try{
//		user = userService.registerNewUser(userDto);
//		} catch (EmailAlreadyExistException e){
//			return null;
//		}
//		return user;		
//	}
	


}
