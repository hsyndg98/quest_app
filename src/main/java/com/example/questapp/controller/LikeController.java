package com.example.questapp.controller;

import com.example.questapp.dto.request.LikeCreateDTO;
import com.example.questapp.dto.response.LikeResponseDTO;
import com.example.questapp.model.Like;
import com.example.questapp.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<LikeResponseDTO> createLike(@RequestBody LikeCreateDTO likeCreateDTO) {
        return ResponseEntity.ok(likeService.createLike(likeCreateDTO));
    }

    @GetMapping
    public ResponseEntity<List<LikeResponseDTO>> getAllLikesWithParams(
            @RequestParam Long userId,
            @RequestParam Long postId) {
        return ResponseEntity.ok(likeService.getAllLikeswithParams(userId, postId));
    }

    @GetMapping("/like/{likeId}")
    public Like getOneLikeById(@PathVariable Long likeId) {
        return likeService.findById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }
}
