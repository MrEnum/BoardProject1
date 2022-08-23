package com.example.boardproject1.repository;

import com.example.boardproject1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
