package com.example.questapp.service.impl;

import com.example.questapp.dto.request.CommentCreateDTO;
import com.example.questapp.dto.request.CommentUpdateDTO;
import com.example.questapp.dto.response.PostResponseDTO;
import com.example.questapp.model.Comment;
import com.example.questapp.model.Post;
import com.example.questapp.model.User;
import com.example.questapp.repository.CommentRepository;
import com.example.questapp.service.CommentService;
import com.example.questapp.service.PostService;
import com.example.questapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    @Override
    public Comment getOneComment(Long commentId) {
        return commentRepository.getById(commentId);
    }

    @Override
    public Comment createOneComment(CommentCreateDTO commentCreateDTO) {
        final User user = userService.getOneUser(commentCreateDTO.getUsername()).get();
        final Post post = postService.getById(commentCreateDTO.getPostId());
        if(user != null && post != null){
            Comment comment = Comment.builder()
                    .user(user)
                    .post(post)
                    .text(commentCreateDTO.getText())
                    .build();
            return commentRepository.save(comment);
        }else{
            return null;
        }

    }

    @Override
    public Comment updateOneComment(Long commentId, CommentUpdateDTO commentUpdateDTO) {
        Comment comment = commentRepository.getById(commentId);
        comment.setText(commentUpdateDTO.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }


}
