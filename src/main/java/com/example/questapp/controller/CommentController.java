package com.example.questapp.controller;

import com.example.questapp.dto.request.CommentCreateDTO;
import com.example.questapp.dto.request.CommentUpdateDTO;
import com.example.questapp.model.Comment;
import com.example.questapp.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam Optional<Long> userId,
                                                        @RequestParam Optional<Long> postId) {

       return ResponseEntity.ok(commentService.getAllCommentsWithParam(userId,postId));

    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Comment> getOneComment(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.getOneComment(commentId));
    }

    @PostMapping()
    public ResponseEntity<Comment> createOneComment(@RequestBody CommentCreateDTO commentCreateDTO){
        return ResponseEntity.ok(commentService.createOneComment(commentCreateDTO));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateDTO commentUpdateDTO){
        return ResponseEntity.ok(commentService.updateOneComment(commentId,commentUpdateDTO));
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.delete(commentId);
    }
}
