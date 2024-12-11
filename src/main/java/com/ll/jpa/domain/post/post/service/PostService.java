package com.ll.jpa.domain.post.post.service;

import com.ll.jpa.domain.post.post.eneity.Post;
import com.ll.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(String title, String content) {
        Post post = new Post(
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                title,
                content,
                false
        );


        postRepository.save(post);
        return post;
    }

    public long count() {
        return postRepository.count();
    }
}
