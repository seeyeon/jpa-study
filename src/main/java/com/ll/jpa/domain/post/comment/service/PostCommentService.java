package com.ll.jpa.domain.post.comment.service;

import com.ll.jpa.domain.post.comment.entity.PostComment;
import com.ll.jpa.domain.post.comment.repository.PostCommentRepository;
import com.ll.jpa.domain.post.post.eneity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    public PostComment write(Post post, String content) {
        PostComment postComment= PostComment.builder()
                .post(post)
                .content(content)
                .build();

        return postCommentRepository.save(postComment);
    }


}
