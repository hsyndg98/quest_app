package com.example.questapp.service;

import com.example.questapp.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts(String username);

    Page<Post> findByUser(String username, Pageable paging);

    Post create(String username, Post post);
}
