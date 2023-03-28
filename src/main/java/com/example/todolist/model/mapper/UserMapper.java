package com.example.todolist.model.mapper;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.response.UserInfoResponse;

public interface UserMapper {

	UserDto toDto(User user);

	UserInfoResponse toResponse(UserDto userDto);

}
