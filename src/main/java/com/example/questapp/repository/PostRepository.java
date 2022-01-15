package com.example.questapp.repository;

import com.example.questapp.model.Post;
import com.example.questapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findPostByUser(User user);

    void deleteById(Long id);
}
