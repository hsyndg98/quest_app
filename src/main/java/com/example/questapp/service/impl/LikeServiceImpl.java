package com.example.questapp.service.impl;

import com.example.questapp.dto.request.LikeCreateDTO;
import com.example.questapp.dto.response.LikeResponseDTO;
import com.example.questapp.model.Like;
import com.example.questapp.model.Post;
import com.example.questapp.model.User;
import com.example.questapp.repository.LikeRepository;
import com.example.questapp.repository.PostRepository;
import com.example.questapp.repository.UserRepository;
import com.example.questapp.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public LikeResponseDTO createLike(LikeCreateDTO likeCreateDTO) {
        User user = userRepository.getById(likeCreateDTO.getUserId());
        Post post = postRepository.getById(likeCreateDTO.getPostId());
        if (user != null && post != null) {
            Like newLike = new Like();
            newLike.setUser(user);
            newLike.setPost(post);
            likeRepository.save(newLike);

            return LikeResponseDTO.builder()
                    .username(newLike.getUser().getUsername())
                    .title(newLike.getPost().getTitle())
                    .build();
        } else {
            return null;
        }

    }

    @Override
    public List<LikeResponseDTO> getAllLikeswithParams(Long userId, Long postId) {
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

        List<Like> likeList;

        if (user != null && post != null) {
            likeList = likeRepository.findByUserIdAndPostId(userId, postId);
        } else if (userId != null) {
            likeList = likeRepository.findByUserId(userId);
        } else if (postId != null) {
            likeList = likeRepository.findByPostId(postId);
        } else {
            likeList = likeRepository.findAll();
        }
        return likeList.stream().map(LikeResponseDTO::new).collect(Collectors.toList());

    }

    @Override
    public Like findById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    @Override
    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
