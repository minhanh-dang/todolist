package com.example.todolist.controller;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.response.AuthenticationResponse;
import com.example.todolist.repository.UserInfoRepository;
import com.example.todolist.security.UserPrincipal;
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

@RestController
@AllArgsConstructor
public class ToDoController {

    @Autowired
    private ToDoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private AuthenticationResponse response;
    private UserInfoRepository userInfoRepository;

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
        return new ResponseEntity<>("Product successfully deleted!",HttpStatus.OK);
    }



}