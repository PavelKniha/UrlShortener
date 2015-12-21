package com.knihapaul.urlshortener.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.knihapaul.urlshortener.config.SpringJPADataConfig;

@ComponentScan({ "com.knihapaul.urlshortener.persistence.dao" })
public class TestConfig extends SpringJPADataConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

}