package com.ll.jpa.global.initData;


import com.ll.jpa.domain.post.comment.service.PostCommentService;
import com.ll.jpa.domain.post.post.eneity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;
    private final PostCommentService postCommentService;

    @Autowired
    @Lazy
    private BaseInitData self;  //프록시를 경유해서가는 객체

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner(){
        return args -> {
            self.work1();
            self.work2();
            self.work3();
        };
    }

    @Transactional
    public void work1(){

        if(postService.count() > 0 ) return;

        Post post1 = postService.write("title1","content1");
        Post post2 = postService.write("title2","content2");
        Post post3 = postService.write("title3","content3");

        post1.addComment("comment1");
        post2.addComment("comment2");
        post3.addComment("comment3");

    }

    @Transactional
    public void work2(){

    }

    @Transactional
    public void work3(){
        //skip
    }

}
