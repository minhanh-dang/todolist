package com.example.todolist.service;

import java.util.List;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.entity.User;

public interface ToDoService {
//	String addUser(User userInfo);
//
//	List<User> addUsers(List<User> userInfos);

//    Optional<User> getUserById(int id);

	ToDoDTO createToDo(ToDoDTO toDoDTO);

//	List<ToDoDTO> createToDos(List<ToDoDTO> toDoDTOS);

	List<ToDoDTO> getAllToDos();

//    ToDoDTO getToDoById(int id);

	ToDoDTO updateToDo(ToDoDTO toDo);

	String deleteToDo(int id);

}
