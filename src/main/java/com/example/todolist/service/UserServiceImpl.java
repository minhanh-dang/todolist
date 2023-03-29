package com.example.todolist.service;

import org.springframework.stereotype.Service;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	@Override
	public UserDto getUserById(Long id) {

		User user = userRepository.findById(id).get();

		UserDto userDto = userMapper.toDto(user);

		return userDto;
	}

}