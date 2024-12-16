package com.ll.jpa.domain.post.post.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest {

	@Autowired
	private PostService postService;

	@Test
	@DisplayName("글 2개 생성")
	@Transactional
	@Rollback
	void t1() {
		postService.write("제목1_test","내용1_test");
		postService.write("제목2_test","내용2_test");
	}

}
