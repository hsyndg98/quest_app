package com.example.questapp.controller;

import com.example.questapp.model.Post;
import com.example.questapp.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{username}/posts")
    public ResponseEntity<Page<Post>> getPageable(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);

        return ResponseEntity.ok(postService.findByUser(username, paging));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam String username) {
        return ResponseEntity.ok(postService.getAllPosts(username));
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestParam String username, @RequestBody Post post){
        return ResponseEntity.ok(postService.create(username,post));
    }

}
