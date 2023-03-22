package com.example.todolist.controller;

import com.example.todolist.model.entity.User;
import com.example.todolist.service.JwtService;
import com.example.todolist.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ToDoController {

    @Autowired
    private ToDoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addUser(@RequestBody User userInfo){
        return service.addUser(userInfo);
    }

    @PostMapping("/addUsers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<User> addUsers(@RequestBody List<User> userInfos){
        return service.addUsers(userInfos);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<User> getUsers(List<User> userInfos){
        return service.getUsers(userInfos);
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    User updateUser(@RequestBody User userInfo){
        return service.updateUser(userInfo);
    }


    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String deleteUser(@PathVariable int id){
        return service.deleteUser(id);
    }
}