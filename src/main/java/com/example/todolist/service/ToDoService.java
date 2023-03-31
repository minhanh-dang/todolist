package com.example.todolist.service;

import java.util.List;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.User;
import com.example.todolist.model.response.ToDoResponse;

public interface ToDoService {
//	String addUser(User userInfo);
//
//	List<User> addUsers(List<User> userInfos);

//    Optional<User> getUserById(int id);

	ToDoDTO createToDo(ToDoDTO toDoDTO);

//	List<ToDoDTO> createToDos(List<ToDoDTO> toDoDTOS);

	List<ToDoDTO> getAllToDos();

	List<ToDoDTO> getUserToDo(Long id);

    ToDoDTO getToDoById(Long id);

	ToDoDTO updateToDo(Long id, ToDoDTO toDo);

	String deleteToDo(Long id);

	ToDoDTO createToDo(Long id, ToDoDTO toDoDTO);

}
