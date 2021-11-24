package com.davablog.davablog.service.serviceImpl;

import com.davablog.davablog.dto.PostDto;
import com.davablog.davablog.model.Post;
import com.davablog.davablog.repository.PostRepository;
import com.davablog.davablog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto CreatePost(PostDto postDto) {

        //Convert DTO to entity
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .description(postDto.getDescription())
                .build();

        Post newPost = postRepository.save(post);

        //Convert entity to DTO
        PostDto postResponse = PostDto.builder()
                .title(newPost.getTitle())
                .content(newPost.getContent())
                .description(newPost.getDescription())
                .build();

        return postResponse;
    }
}
