package com.knihapaul.urlshortener.web.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knihapaul.urlshortener.persistence.dao.UserRepository;
import com.knihapaul.urlshortener.persistence.model.User;


@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getAllUsers(Map<String, Object> model){
				
		Collection<User> users = userRepository.findAll();
		model.put("selections", users);
		return "home";
		
	}

}
