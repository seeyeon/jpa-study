package com.ll.jpa.global.initData;


import com.ll.jpa.domain.post.post.eneity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import com.ll.jpa.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    @Order(1)
    public ApplicationRunner BaseInitData1ApplicationRunner(){
        return ApplicationArguments -> {
            System.out.println("BaseInitData1ApplicationRunner");

            if(postService.count() >0) return;

            Post post1 = postService.write("title1", "content1");
            Post post2 = postService.write("title2", "content2");
            Post post3 = postService.write("title3", "content3");

        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner BaseInitData2ApplicationRunner() {
        return new ApplicationRunner() {

            @Override
            @Transactional
            public void run(ApplicationArguments args) throws Exception {
                System.out.println("BaseInitData2ApplicationRunner");

                Ut.thread.sleep(1000);

                Post post1 = postService.findById(1).get();
                postService.modify(post1, "제목 수정됨", "내용 수정됨");
            }
        };
    }
}
