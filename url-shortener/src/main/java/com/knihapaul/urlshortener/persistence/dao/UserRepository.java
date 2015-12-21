package com.knihapaul.urlshortener.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knihapaul.urlshortener.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
