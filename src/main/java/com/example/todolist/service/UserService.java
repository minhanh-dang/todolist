package com.example.todolist.service;

import java.util.List;

import com.example.todolist.model.DTO.UserDto;

public interface UserService {

	UserDto getUserById(Long id);

	UserDto addUser(UserDto userDto);

	List<UserDto> getAllUsers();

	UserDto updateUser(UserDto userInfo);

	String deleteUser(Long id);

}
