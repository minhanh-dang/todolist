package com.example.todolist.model.mapper;

import org.springframework.stereotype.Component;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.request.UserRequest;
import com.example.todolist.model.response.UserInfoResponse;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDto toDto(User user) {

		if (user == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getUserName());

		return userDto;
	}

	@Override
	public UserInfoResponse toResponse(UserDto userDto) {

		UserInfoResponse response = new UserInfoResponse();
		response.setId(userDto.getId());
		response.setName(userDto.getName());

		return response;
	}

	@Override
	public UserDto toDto(UserRequest request) {

		UserDto userDto = new UserDto();
		userDto.setName(request.getName());
		userDto.setPassword(request.getPassword());

		return userDto;
	}

}