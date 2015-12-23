package com.knihapaul.urlshortener.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.knihapaul.urlshortener.persistence.dao.PrivilegeRepository;
import com.knihapaul.urlshortener.persistence.dao.RoleRepository;
import com.knihapaul.urlshortener.persistence.dao.UserRepository;
import com.knihapaul.urlshortener.persistence.model.Privilege;
import com.knihapaul.urlshortener.persistence.model.Role;
import com.knihapaul.urlshortener.persistence.model.User;

@Component
public class SetupDataLoader implements	ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadyInstall = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadyInstall) {
			return;
		}
		Privilege readPrivilege = createPrivilege("READ_PRIVILEGE");
		Privilege writePrivilege = createPrivilege("WRITE_PRIVILEGE");

		createRole("ROLE_USER", Arrays.asList(readPrivilege, writePrivilege));

		Role userRole = roleRepository.findByName("ROLE_USER");
		User user = new User();
		user.setFirstName("Ivan");
		user.setLastName("Ivanov");
		user.setPassword(passwordEncoder.encode("Pasha@123"));
		user.setEmail("iceways@mail.ru");
		user.addRole(userRole);
		user.setEnabled(true);
		
		userRepository.save(user);
		alreadyInstall = true;
 
	}

	@Transactional
	private Privilege createPrivilege(String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRole(String name, Collection<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			for (Privilege privilege : privileges) {
				role.addPrivilege(privilege);
			}
			roleRepository.save(role);
		}
		return role;
	}

}
