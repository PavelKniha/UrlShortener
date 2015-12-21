package com.knihapaul.urlshortener.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@TransactionConfiguration
public class RolesIntegrationTest {
	
	public RolesIntegrationTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void test(){};
	
}
