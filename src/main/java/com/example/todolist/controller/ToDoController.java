package com.example.todolist.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.entity.User;
import com.example.todolist.service.ToDoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ToDoController {

	private final ToDoService service;

	@PostMapping("/addUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addUser(@RequestBody User userInfo) {
		return service.addUser(userInfo);
	}

	@PostMapping("/addUsers")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	List<User> addUsers(@RequestBody List<User> userInfos) {
		return service.addUsers(userInfos);
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	List<User> getUsers(List<User> userInfos) {
		return service.getUsers(userInfos);
	}

	@PutMapping("/updateUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	User updateUser(@RequestBody User userInfo) {
		return service.updateUser(userInfo);
	}

	@DeleteMapping("/deleteUser/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	String deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}
}