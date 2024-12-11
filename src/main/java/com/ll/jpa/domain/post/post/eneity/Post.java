package com.ll.jpa.domain.post.post.eneity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    //long => null 안됨
    //Long => null 가능
    //JPA 엔티티 클래스 특성상 id 필드는 null이 가능하도록 해야함
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Setter(AccessLevel.PRIVATE)

    private Long id;

    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedAt;

    @Column(length=100)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private boolean blind;
}
