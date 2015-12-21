package com.knihapaul.urlshortener.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knihapaul.urlshortener.persistence.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

}
