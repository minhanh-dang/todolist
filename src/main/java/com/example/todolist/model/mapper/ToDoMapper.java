package com.example.todolist.model.mapper;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.entity.ToDo;

public class ToDoMapper {

    private static ToDoMapper INSTANCE;

    public static ToDoMapper getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ToDoMapper();
        }
        return INSTANCE;
    }

    public ToDo toEntity(ToDoDTO toDoDTO){
        ToDo toDo = new ToDo();
        toDo.setId(toDoDTO.getId());
        toDo.setName(toDoDTO.getName());
        toDo.setStatus(toDoDTO.getStatus());
        return toDo;
    }

    public ToDoDTO toDTO(ToDo toDo){
        ToDoDTO toDoDTO = new ToDoDTO();
        toDoDTO.setId(toDo.getId());
        toDoDTO.setName(toDo.getName());
        toDoDTO.setStatus(toDo.getStatus());
        return toDoDTO;
    }
}
