package com.example.post.posts;

import com.example.post.entities.Post;
import com.example.post.posts.interfaces.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService {

    private final PostsRepository postsRepository;

    @Override
    public List<Post> find(){
        return this.postsRepository.findAll();
    }
}
