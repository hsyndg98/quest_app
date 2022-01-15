package com.example.questapp.dto.request;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private String username;
    private Long postId;
    private String text;
}
