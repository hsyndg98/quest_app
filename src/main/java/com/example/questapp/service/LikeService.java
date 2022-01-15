package com.example.questapp.service;

import com.example.questapp.dto.request.LikeCreateDTO;
import com.example.questapp.dto.response.LikeResponseDTO;
import com.example.questapp.model.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    LikeResponseDTO createLike(LikeCreateDTO likeCreateDTO);

    List<LikeResponseDTO> getAllLikeswithParams(Long userId, Long postId);

    Like findById(Long likeId);

    void deleteOneLikeById(Long likeId);
}
