package com.example.post.posts;

import com.example.post.entities.Post;
import com.example.post.posts.interfaces.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final IPostsService IpostsService;

    @GetMapping("")
    public List<Post> findAll() {
        return this.IpostsService.find();
    }
}
