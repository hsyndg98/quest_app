package com.example.questapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Like {

    @Id
    private Long id;

    private Long postId;
    private Long userId;
}
