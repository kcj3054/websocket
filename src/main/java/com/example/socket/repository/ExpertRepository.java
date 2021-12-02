package com.example.socket.repository;

import com.example.socket.entity.Expert;
import com.example.socket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long> {

    Optional<User> findByUsername(String username);
}
