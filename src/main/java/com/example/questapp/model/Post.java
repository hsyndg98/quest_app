package com.example.questapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class Post {

    @Id
    private Long id;
    private Long userId;
    private String title;
    @Lob
    private String text;
}
