package com.example.todolist.service;

import com.example.todolist.model.request.LoginRequest;
import com.example.todolist.model.response.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse authenticate(LoginRequest request);
}
