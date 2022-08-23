package com.example.boardproject1.repository;

import com.example.boardproject1.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    List<Comments> findAllByPostId(int postId);
}
