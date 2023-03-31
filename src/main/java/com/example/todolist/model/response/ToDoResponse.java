package com.example.todolist.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ToDoResponse {

    private Long id;
    private String name;
    private String status;
    private Date createdAt;
}
