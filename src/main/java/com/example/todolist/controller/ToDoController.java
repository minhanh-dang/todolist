package com.example.todolist.controller;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.response.AuthenticationResponse;

import com.example.todolist.service.JwtService;
import com.example.todolist.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ToDoController {

    @Autowired
    private ToDoService service;

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addUser(@RequestBody User userInfo) {
        return service.addUser(userInfo);
    }

    @PostMapping("/addUsers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<List<User>> addUsers(@RequestBody List<User> userInfos) {
        List<User> users = service.addUsers(userInfos);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<List<User>> getUsers(List<User> userInfos) {
        List<User> users = service.getUsers(userInfos);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<User> updateUser(@RequestBody User userInfo) {
        User updatedUser = service.updateUser(userInfo);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<String> deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }
}