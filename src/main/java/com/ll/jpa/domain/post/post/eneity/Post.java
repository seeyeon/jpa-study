package com.ll.jpa.domain.post.post.eneity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class Post {
    //long => null 안됨
    //Long => null 가능
    //JPA 엔티티 클래스 특성상 id 필드는 null이 가능하도록 해야함
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    @Column(length=100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private boolean blind;
}
