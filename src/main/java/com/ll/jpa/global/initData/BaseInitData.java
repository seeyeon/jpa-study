package com.ll.jpa.global.initData;


import com.ll.jpa.domain.post.comment.entity.PostComment;
import com.ll.jpa.domain.post.comment.service.PostCommentService;
import com.ll.jpa.domain.post.post.eneity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
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
    @Order(1)
    public ApplicationRunner baseInitData1ApplicationRunner(){
        return new ApplicationRunner() {

            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if(postService.count() >0) return;

                Post post1 = postService.write("title1", "content1");
                Post post2 = postService.write("title2", "content2");
                Post post3 = postService.write("title3", "content3");

                //1번 글에 대한 댓글
                PostComment postComment1 = postCommentService.write(post1, "comment1");

                //2번 글에 대한 댓글
                PostComment postComment2 = postCommentService.write(post2, "comment2");

                //3번 글에 대한 댓글
                PostComment postComment3 = postCommentService.write(post3, "comment3");

            }
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner baseInitData2ApplicationRunner() {
        return args -> self.work();
    }


    @Transactional
    public void work(){

            PostComment postComment3 = postCommentService.findById(3).get();
            // select * from post_comment where id=3;

            Post postOfComment3 = postComment3.getPost();
            System.out.println("postOfComment3.id"+postOfComment3.getId());
            System.out.println("postOfComment3.Title"+postOfComment3.getTitle());

            // select * from post where id=2;
    }


}
