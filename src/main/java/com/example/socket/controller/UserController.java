package com.example.socket.controller;


import com.example.socket.dto.CreateUserRequest;
import com.example.socket.entity.User;
import com.example.socket.repository.UserRepository;
import com.example.socket.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //    ModelAttribute는 생략 가능
    @PostMapping("/login")
    public String login(@ModelAttribute("request") CreateUserRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login";
        }
        Optional<User> one = userService.findOne(request.getUsername());
        if (one.isPresent()) {
            return "/webSocketClient";
        }
        return "/login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }


    //    회원가입
    @ResponseBody
    @PostMapping("/sign_up")
    public String save(@Validated @RequestBody User user, BindingResult result) {

        log.info(user.getUsername());

        if (result.hasErrors()) {
            return "에러입니다";
        }
        userRepository.save(user);
        return "회원가입성공";
    }
}
