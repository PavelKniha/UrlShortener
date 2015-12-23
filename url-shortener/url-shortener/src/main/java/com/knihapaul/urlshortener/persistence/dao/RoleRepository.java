package com.knihapaul.urlshortener.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knihapaul.urlshortener.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
