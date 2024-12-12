package com.ll.jpa.domain.post.comment.entity;

import com.ll.jpa.domain.post.post.eneity.Post;
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
public class PostComment {

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

    @ManyToOne(fetch = FetchType.EAGER) //주키 타입이 저장된다.
    private Post post; //post와 연결되어 있는 리모콘을 가진다.

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean blind; //관리자에 의해 숨김처리 될 수 있음

}
