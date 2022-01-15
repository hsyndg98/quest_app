package com.example.questapp.service.impl;

import com.example.questapp.dto.request.PostCreateDTO;
import com.example.questapp.dto.request.PostUpdateDTO;
import com.example.questapp.dto.response.PostResponseDTO;
import com.example.questapp.model.Post;
import com.example.questapp.model.User;
import com.example.questapp.repository.PostRepository;
import com.example.questapp.service.PostService;
import com.example.questapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


    @Override
    public Post create(PostCreateDTO postCreateDTO) {
        User user = userService
                .getOneUser(postCreateDTO.getUsername())
                .orElse(null);

        final Post post = Post.builder()
                .user(user)
                .title(postCreateDTO.getTitle())
                .text(postCreateDTO.getText())
                .build();

        return postRepository.save(post);
    }

    @Override
    public PostResponseDTO getOnePost(Long id) {
        final Post post = postRepository.getById(id);
        if (Objects.isNull(post)) {
            return null;
        } else {
            return PostResponseDTO.builder()
                    .username(post.getUser().getUsername())
                    .title(post.getTitle())
                    .text(post.getText())
                    .build();
        }
    }

    @Override
    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public PostResponseDTO updateOnePost(Long postid, PostUpdateDTO updatePost) {
        final Post post = postRepository.getById(postid);
        if(Objects.isNull(post)){
            return null;
        }else{
            post.setText(updatePost.getText());
            post.setTitle(updatePost.getTitle());
            Post newPost = postRepository.save(post);
            return PostResponseDTO.builder()
                    .text(newPost.getText())
                    .title(newPost.getTitle())
                    .username(newPost.getUser().getUsername())
                    .build();
        }
    }

    @Override
    public void deleteOnePost(Long postid) {
        postRepository.deleteById(postid);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }


    @Override
    public List<PostResponseDTO> getAllPosts(String username) {
        User user = userService.getOneUser(username).orElse(null);
        return postRepository
                .findPostByUser(user)
                .stream()
                .map(post1 -> PostResponseDTO.builder()
                        .username(post1.getUser().getUsername())
                        .title(post1.getTitle())
                        .text(post1.getText())
                        .build())
                .collect(Collectors.toList());
    }


}
