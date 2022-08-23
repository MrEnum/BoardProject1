package com.example.boardproject1.repository;

import com.example.boardproject1.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findByUserIdAndId(int userId, int postId);
}
