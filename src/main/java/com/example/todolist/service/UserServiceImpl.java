package com.example.todolist.service;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.RoleName;
import com.example.todolist.model.entity.Roles;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.repository.RoleRepository;
import com.example.todolist.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final PasswordEncoder encoder;

	private final RoleRepository roleRepository;

	@Override
	public UserDto getUserById(Long id) {

		User user = userRepository.findById(id).get();

		UserDto userDto = userMapper.toDto(user);

		return userDto;
	}

	@Override
	public UserDto addUser(UserDto userDto) {

		User user = new User();
		user.setUserName(userDto.getName());
		user.setPassword(encoder.encode(userDto.getPassword()));
		Roles role = roleRepository.findByRoleName(RoleName.ROLE_USER).get();
		user.setRoles(Collections.singleton(role));

		User addUser = userRepository.save(user);

		UserDto addUserDto = userMapper.toDto(addUser);

		return addUserDto;
	}

}