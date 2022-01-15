package com.example.questapp.dto.response;

import com.example.questapp.model.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDTO {

    String username;
    String title;

    public LikeResponseDTO(Like like) {
        this.username = like.getUser().getUsername();
        this.title = like.getPost().getTitle();
    }
}
