package com.example.posts;

import com.example.common.libs.entities.Post;
import com.example.posts.dto.request.CreatePostDto;
import com.example.posts.interfaces.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService implements IPostsService {
    private final PostsRepository postsRepository;

    @Override
    public void create(CreatePostDto createPostDto) {
        this.postsRepository.save(Post.builder().title(createPostDto.getTitle()).content(createPostDto.getContent()).build());
    }

    @Override
    public List<Post> find(){
        return this.postsRepository.findAll();
    }
}
