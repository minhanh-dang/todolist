package com.example.todolist.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.model.response.UserInfoResponse;
import com.example.todolist.security.CurrentUser;
import com.example.todolist.security.UserPrincipal;
import com.example.todolist.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserInfoResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {

        UserDto userDto = userService.getUserById(currentUser.getId());

        return userMapper.toResponse(userDto);
    }
}