package com.example.common.libs.entities;

import com.example.common.libs.entities.abs.AbsDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "posts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DiscriminatorValue("POST")
public class Post extends AbsDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(comment = "게시글명")
    private String title;

    @Column(comment = "게시글 내용")
    private String content;
}