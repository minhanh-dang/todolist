package com.example.todolist.service;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.User;

import java.util.List;

public interface UserService {

	UserDto getUserById(Long id);

	UserDto addUser(UserDto userDto);

	List<UserDto> getAllUsers();

	User updateUser(User userInfo);

	String deleteUser(int id);

}
