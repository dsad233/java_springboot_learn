package com.example.posts;

import com.example.common.dto.ResponseDto;
import com.example.common.libs.entities.Post;
import com.example.posts.dto.request.CreatePostDto;
import com.example.posts.interfaces.IPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostsController {
    private final IPostsService iPostsService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> create(@RequestBody CreatePostDto createPostDto) {
        this.iPostsService.create(createPostDto);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode(HttpStatus.CREATED.value());
        responseDto.setMessage("Success");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> findAll() {
        return this.iPostsService.find();
    }
}
