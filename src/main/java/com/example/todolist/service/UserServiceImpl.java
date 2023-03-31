package com.example.todolist.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.todolist.model.request.UserRequest;
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


	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(user -> userMapper.toDto(user))
				.collect(Collectors.toList());
	}

	//	@Override
//	public String addUser(User userInfo) {
//		userInfo.setUserName(userInfo.getUserName());
//		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//		userInfoRepository.save(userInfo);
//		return "User added";
//	}
//
//	@Override
//	public List<User> addUsers(List<User> userInfos) {
//		return userInfoRepository.saveAll(userInfos);
//	}

//	@Override
//	public List<User> getUsers(List<User> userInfos) {
//		return userInfoRepository.findAll();
//	}

	@Override
	public UserDto updateUser(UserDto userInfo) {
//        User existingUser = userInfoRepository.findById(userInfo.getId()).orElse(null);
//        existingUser.setUserName(userInfo.getUserName());
//        existingUser.setPassword(userInfo.getPassword());
//        return userInfoRepository.save(existingUser);
		return null;
	}

	@Override
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
//		return "User deleted: " + id;
		return null;
	}
}