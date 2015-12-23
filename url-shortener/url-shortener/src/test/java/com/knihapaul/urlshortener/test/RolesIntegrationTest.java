package com.knihapaul.urlshortener.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.knihapaul.urlshortener.persistence.dao.PrivilegeRepository;
import com.knihapaul.urlshortener.persistence.dao.RoleRepository;
import com.knihapaul.urlshortener.persistence.dao.UserRepository;
import com.knihapaul.urlshortener.persistence.model.Privilege;
import com.knihapaul.urlshortener.persistence.model.Role;
import com.knihapaul.urlshortener.persistence.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@TransactionConfiguration
public class RolesIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EntityManager entityManager;

	@Test
	public void testDeletePrivilege() {
		String roleName = "TEST_ROLE";
		String privilegeName = "TEST_PRIVILEGE";
		Privilege privilege = new Privilege(privilegeName);
		privilege = privilegeRepository.save(privilege);

		Role role = new Role(roleName);
		role.addPrivilege(privilege);
		role = roleRepository.save(role);

		assertNotNull(role);
		assertNotNull(privilege);
		privilegeRepository.delete(privilege);
		
		// manual commit transaction to prevent OOM when fetching privileges
		entityManager.flush();
		entityManager.clear();

		assertNull(privilegeRepository.findByName(privilegeName));
		assertNotNull(roleRepository.findByName(roleName));
		assertTrue(roleRepository.findByName(roleName).getPrivileges().isEmpty());

	}
	
	@Test
	public void testDeleteRole(){
		String roleName = "TEST_ROLE";
		String writePrivilegeName = "TEST_WRITE_PRIVILEGE";
		String readPrivilegeName = "TEST_READ_PRIVILEGE";
		
		Privilege writePrivilege = new Privilege(writePrivilegeName);
		Privilege readPrivilege = new Privilege(readPrivilegeName);
		writePrivilege = privilegeRepository.save(writePrivilege);
		readPrivilege = privilegeRepository.save(readPrivilege);

		Role role = new Role(roleName);
		role.addPrivilege(writePrivilege);
		role.addPrivilege(readPrivilege);
		role = roleRepository.save(role);
		
		assertNotNull(role);
		assertNotNull(writePrivilege);
		assertNotNull(readPrivilege);		
		
		role.removeAllPrivileges();
		roleRepository.delete(role);
		entityManager.flush();
		entityManager.clear();
		
		role = roleRepository.findByName(roleName);
		writePrivilege = privilegeRepository.findByName(writePrivilegeName);
		readPrivilege = privilegeRepository.findByName(readPrivilegeName);
		
		assertNull(role);
		assertNotNull(writePrivilege);
		assertNotNull(readPrivilege);
		assertFalse(writePrivilege.getRoles().contains(role));
		assertFalse(readPrivilege.getRoles().contains(role));			
	}
	
	@Test
	public void testDeleteUser(){
		String roleName = "TEST_ROLE";
		Role role = new Role(roleName);
		role = roleRepository.save(role);
		
		assertNotNull(role);
		
		User user = new User();
		user.setEmail("ivanov@ivan.com");
		user.setLastName("Ivanov");
		user.setFirstName("Ivan");
		user.setPassword(passwordEncoder.encode("ivanov"));
		user.addRole(role);
		user.setEnabled(true);
		user = userRepository.save(user);
		
		assertNotNull(user);
		
		user.removeAllRoles();
		userRepository.delete(user);
		entityManager.flush();
		entityManager.clear();
		
		assertNull(userRepository.findByEmail("ivanov@ivan.com"));
		assertNotNull(roleRepository.findByName(roleName));
		assertFalse(roleRepository.findByName(roleName).getUsers().contains(user));		
		
	}
}
