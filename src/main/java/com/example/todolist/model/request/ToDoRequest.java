package com.example.todolist.model.request;

import com.example.todolist.model.entity.ToDoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoRequest {

	private String name;
	private ToDoStatus status;

}
