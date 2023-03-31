package com.example.todolist.service;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.request.UserRequest;

import java.util.List;

public interface UserService {

	UserDto getUserById(Long id);

	UserDto addUser(UserDto userDto);

	List<UserDto> getAllUsers();

	UserDto updateUser(UserDto userInfo);

	String deleteUser(Long id);

}
