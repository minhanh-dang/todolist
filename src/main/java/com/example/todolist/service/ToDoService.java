package com.example.todolist.service;

import java.util.List;

import com.example.todolist.model.DTO.ToDoDTO;

public interface ToDoService {

	ToDoDTO createToDo(ToDoDTO toDoDTO);

	List<ToDoDTO> getAllToDos();

	List<ToDoDTO> getUserToDo(Long id);

    ToDoDTO getToDoById(Long id);

	ToDoDTO updateToDo(Long id, ToDoDTO toDo);

	String deleteToDo(Long id);

	ToDoDTO createToDo(Long id, ToDoDTO toDoDTO);

}
