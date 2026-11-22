package com.example.posts;

import com.example.common.libs.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, String> {
}
