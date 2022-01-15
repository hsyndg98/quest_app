package com.example.questapp.dto.request;

import lombok.Data;

@Data
public class PostCreateDTO {

    private String username;
    private String title;
    private String text;

}
