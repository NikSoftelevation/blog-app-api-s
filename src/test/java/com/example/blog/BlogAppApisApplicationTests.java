package com.example.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.blog.repository.UserRepository;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {

		String className = userRepository.getClass().getName();

		String packageName = userRepository.getClass().getPackageName();

		System.out.println(className);

		System.out.println(packageName);
	}

}
