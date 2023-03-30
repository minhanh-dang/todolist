package com.example.todolist.model.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoResponse {

	private Long id;
	private String name;
	private String status;
	private Instant createdAt;

}
