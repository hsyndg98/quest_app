package com.example.questapp.service.impl;

import com.example.questapp.model.Post;
import com.example.questapp.model.User;
import com.example.questapp.repository.PostRepository;
import com.example.questapp.service.PostService;
import com.example.questapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


    @Override
    public Page<Post> findByUser(String username, Pageable paging) {
        return postRepository.findByUser(username, paging);
    }

    @Override
    public Post create(String username, Post post) {
        post.setUser(userService.getOneUser(username).orElse(null));
        return postRepository.save(post);
    }


    @Override
    public List<Post> getAllPosts(String username) {
        User user = userService.getOneUser(username).orElse(null);
        List<Post> post = postRepository.findPostByUser(user);
        return post;
    }


}
