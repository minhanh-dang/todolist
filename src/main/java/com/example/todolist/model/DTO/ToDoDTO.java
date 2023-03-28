package com.example.todolist.model.DTO;


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
    private int id;
    private int role_id;
    private String name;
    private ToDoStatus status;
    private java.sql.Date dateCreated;
}
