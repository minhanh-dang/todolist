package com.example.todolist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.model.request.UserRequest;
import com.example.todolist.model.response.UserInfoResponse;
import com.example.todolist.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ToDoController {

	private final UserMapper userMapper;

	private final UserService userService;

	@PostMapping("/addUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public UserInfoResponse addUser(@RequestBody UserRequest request) {

		UserDto userDto = userMapper.toDto(request);
		UserDto user = userService.addUser(userDto);

		return userMapper.toResponse(user);
	}

//	@PostMapping("/addUsers")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	ResponseEntity<List<User>> addUsers(@RequestBody List<UserRequest> requests) {
//		UserDto userDto = userMapper.toDto(request);
//		List<User> users = service.addUsers(userInfos);
//		return new ResponseEntity<>(users, HttpStatus.OK);
//	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	ResponseEntity<List<UserDto>> getUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	ResponseEntity<User> updateUser(@RequestBody User userInfo) {
		User updatedUser = userService.updateUser(userInfo);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	ResponseEntity<String> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}
}