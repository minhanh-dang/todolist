package com.example.todolist.model.DTO;


import com.example.todolist.model.entity.ToDoStatus;
import com.example.todolist.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
    private int id;
    private Long userId;
    private String name;
    private ToDoStatus status;
    private java.sql.Date dateCreated;
}
