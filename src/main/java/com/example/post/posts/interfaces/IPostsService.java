package com.example.post.posts.interfaces;

import com.example.post.entities.Post;

import java.util.List;

public interface IPostsService {
    List<Post> find();
}
