package com.davablog.davablog.service;

import com.davablog.davablog.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto CreatePost(PostDto postDto);
    List<PostDto> getAllPosts();
}
