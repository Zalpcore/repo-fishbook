package com.fishbook.fishbook;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fishbook.application.FishbookProjApplication;
import com.fishbook.application.controller.UserController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FishbookProjApplication.class)
@SpringBootTest
class FishbookProjApplicationTests {

	@Autowired
	private UserController userController;
	
	
	
	@Test()
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	
	
	
}
