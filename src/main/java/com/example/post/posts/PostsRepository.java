package com.example.post.posts;

import com.example.post.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, String> {
}
