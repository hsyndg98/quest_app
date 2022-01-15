package com.example.questapp.service;

import com.example.questapp.dto.request.PostCreateDTO;
import com.example.questapp.dto.request.PostUpdateDTO;
import com.example.questapp.dto.response.PostResponseDTO;
import com.example.questapp.model.Post;

import java.util.List;

public interface PostService {
    List<PostResponseDTO> getAllPosts(String username);

    Post create(PostCreateDTO postCreateDTO);

    PostResponseDTO getOnePost(Long id);

    Post getById(Long id);

    PostResponseDTO updateOnePost(Long postid, PostUpdateDTO updatePost);

    void deleteOnePost(Long postid);

    List<Post> findAllPosts();

}
