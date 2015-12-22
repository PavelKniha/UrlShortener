package com.knihapaul.urlshortener.persistence.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knihapaul.urlshortener.exception.EmailAlreadyExistException;
import com.knihapaul.urlshortener.persistence.dao.RoleRepository;
import com.knihapaul.urlshortener.persistence.dao.UserRepository;
import com.knihapaul.urlshortener.persistence.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
	public User registerNewUser(UserDto userDto){
		if(emailExists(userDto.getEmail())){
			throw new EmailAlreadyExistException("User with this email: " + userDto.getEmail() + " already exists");
		}
		User user = new User();
		BeanUtils.copyProperties(userDto, user, "password");
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEnabled(true);
		user.addRole(roleRepository.findByName("ROLE_USER"));
		return userRepository.save(user);	
	}

	protected boolean emailExists(String email) {
		User user = userRepository.findByEmail(email);
		if(user != null){
			return true;
		}
		return false;	
	}
	

}
