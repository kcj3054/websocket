package com.example.socket.service;

import com.example.socket.dto.CreateUserRequest;
import com.example.socket.entity.Expert;
import com.example.socket.entity.User;
import com.example.socket.repository.ExpertRepository;
import com.example.socket.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ExpertService {

    private ExpertRepository expertRepository;

    @Transactional
    public void createUserService(CreateUserRequest request) {
        expertRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new RuntimeException("중복된 username");
                });

        expertRepository.save(
                Expert
                        .builder()
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .build()
        );
    }

    /**
     * 회원 조회
     */

    public List<Expert> findAll() {
        return expertRepository.findAll();
    }
}
