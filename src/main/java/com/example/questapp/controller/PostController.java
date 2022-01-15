package com.example.questapp.controller;

import com.example.questapp.dto.request.PostCreateDTO;
import com.example.questapp.dto.request.PostUpdateDTO;
import com.example.questapp.dto.response.PostResponseDTO;
import com.example.questapp.model.Post;
import com.example.questapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponseDTO>> getAllPost(@PathVariable String username) {

        return ResponseEntity.ok(postService.getAllPosts(username));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost2() {
        return new ResponseEntity(postService.findAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/post/{postid}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long postid) {

        return ResponseEntity.ok(postService.getOnePost(postid));
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostCreateDTO postCreateDTO) {
        return ResponseEntity.ok(postService.create(postCreateDTO));
    }

    @PutMapping("/post/{postid}")
    public ResponseEntity<PostResponseDTO> updateOnePost(@PathVariable Long postid, @RequestBody PostUpdateDTO updatePost) {
        return ResponseEntity.ok(postService.updateOnePost(postid, updatePost));
    }

    @DeleteMapping("/post/{postid}")
    public void deleteOnePost(@PathVariable Long postid) {
        postService.deleteOnePost(postid);
        log.info("Post silindi");
    }


}
