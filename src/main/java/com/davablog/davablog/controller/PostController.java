package com.davablog.davablog.controller;

import com.davablog.davablog.dto.PostDto;
import com.davablog.davablog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.CreatePost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPosts();
    }
}
