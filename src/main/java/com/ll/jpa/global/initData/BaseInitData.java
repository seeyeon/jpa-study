package com.ll.jpa.global.initData;


import com.ll.jpa.domain.post.comment.entity.PostComment;
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

import java.util.List;

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

        post1.addComment("comment1");  //트랜잭션이 끝난 뒤 일괄 정산처럼 10초 뒤에 실행된다.
        post1.addComment("comment2");
        post3.addComment("comment3");

    }

    @Transactional
    public void work2(){

        Post post = postService.findById(1).get();
        System.out.println("1번글 로드 완료");

        List<PostComment> postComments = post.getComments(); //Lazy --> DATA가 없다., postComments는 프록시 객체
        System.out.println("1번글들의 댓글 로드 완료");

        PostComment postComment1 = postComments.get(0); //select발생(이왕가져오는거 댓글 한번에 리스트가져옴)
        System.out.println("1번글의 첫번째 댓글 로드 완료");

        PostComment postComment2 = postComments.get(1);
        System.out.println("1번글의 두번째 댓글 로드 완료");

    }

    @Transactional
    public void work3(){
        //skip
    }

}
