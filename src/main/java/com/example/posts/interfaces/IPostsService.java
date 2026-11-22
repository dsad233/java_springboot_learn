package com.example.posts.interfaces;

import com.example.common.libs.entities.Post;
import com.example.posts.dto.request.CreatePostDto;

import java.util.List;

public interface IPostsService {
    void create(CreatePostDto createPostDto);
    List<Post> find();
}
