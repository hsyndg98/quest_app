package com.example.questapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class Comment {

    @Id
    private Long id;

    private Long userId;
    private Long postId;
    @Lob
    private String text;

}
