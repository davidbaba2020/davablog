package com.davablog.davablog.service.serviceImpl;

import com.davablog.davablog.dto.PostDto;
import com.davablog.davablog.exceptions.ResourceNotFoundException;
import com.davablog.davablog.model.Post;
import com.davablog.davablog.repository.PostRepository;
import com.davablog.davablog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto CreatePost(PostDto postDto) {

        //Convert DTO to entity
        Post post = dtoMapToEntity(postDto);
        Post newPost = postRepository.save(post);


        //Convert entity to DTO
        PostDto postResponse = entityMapToDTO(post);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> entityMapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        return entityMapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post updatedPost = postRepository.save(post);
        return entityMapToDTO(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }


    private PostDto entityMapToDTO(Post post){
        PostDto newPostDto = PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .description(post.getDescription())
                .build();
        return newPostDto;
    }

    private Post dtoMapToEntity(PostDto postDto){
        Post newPost = Post.builder()
                .postId(postDto.getPostId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .description(postDto.getDescription())
                .build();
        return newPost;
    }
}
