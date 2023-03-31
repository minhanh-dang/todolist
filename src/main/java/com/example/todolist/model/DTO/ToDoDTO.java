package com.example.todolist.model.DTO;

import java.util.Date;


import com.example.todolist.model.entity.ToDoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
    private Long id;
    private String name;
    private ToDoStatus status;
    private Date dateCreated;
    private UserDto userDto;

}
