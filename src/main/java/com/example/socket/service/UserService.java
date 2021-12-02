package com.example.socket.service;

import com.example.socket.dto.CreateUserRequest;
import com.example.socket.entity.User;
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
public class UserService {

    private UserRepository userRepository;

    /***
     *
     * 로그인 @return null 로그인 실패
     */
    public User login(String username, String password) {
         return userRepository.findByUsername(username)
                 .filter( m -> m.getPassword().equals(password))
                .orElse(null);
    }

    @Transactional
    public void createUserService(CreateUserRequest request) {
        userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new RuntimeException("중복된 username");
                });

        userRepository.save(
                User
                        .builder()
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .build()
        );
    }

    /**
     * 회원 조회
     */

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(String username)
    {
        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자 정보 없음")));

    }

}
