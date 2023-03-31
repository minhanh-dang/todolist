package com.example.todolist.model.mapper;

import org.springframework.stereotype.Component;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.request.ToDoRequest;
import com.example.todolist.model.response.ToDoResponse;

@Component
public class ToDoMapper {

	private static ToDoMapper INSTANCE;

	public static ToDoMapper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ToDoMapper();
		}
		return INSTANCE;
	}

	public ToDo toEntity(ToDoDTO toDoDTO) {
		ToDo toDo = new ToDo();
		toDo.setId(toDoDTO.getId());
		toDo.setName(toDoDTO.getName());
		toDo.setStatus(toDoDTO.getStatus());
		return toDo;
	}

	public ToDoDTO toDTO(ToDo toDo) {
		ToDoDTO toDoDTO = new ToDoDTO();
		toDoDTO.setId(toDo.getId());
		toDoDTO.setName(toDo.getName());
		toDoDTO.setStatus(toDo.getStatus());
		toDoDTO.setDateCreated(toDo.getCreatedAt());
		toDoDTO.setUserDto(toDoDTO.getUserDto());
		return toDoDTO;
	}

	public ToDoDTO toDto(ToDoRequest request) {
		ToDoDTO toDoDTO = new ToDoDTO();
		toDoDTO.setName(request.getName());
		return toDoDTO;
	}

	public ToDoResponse toResponse(ToDoDTO toDo) {
		ToDoResponse response = new ToDoResponse();
		response.setId(toDo.getId());
		response.setName(toDo.getName());
		response.setStatus(toDo.getStatus().name());

		response.setCreatedAt(toDo.getDateCreated());
		return response;
	}

}
