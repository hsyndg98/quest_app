package com.example.questapp.service;

import com.example.questapp.dto.request.CommentCreateDTO;
import com.example.questapp.dto.request.CommentUpdateDTO;
import com.example.questapp.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

    Comment getOneComment(Long commentId);

    Comment createOneComment(CommentCreateDTO commentCreateDTO);

    Comment updateOneComment(Long commentId, CommentUpdateDTO commentUpdateDTO);

    void delete(Long commentId);
}
