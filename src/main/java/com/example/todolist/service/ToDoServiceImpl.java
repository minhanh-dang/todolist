package com.example.todolist.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.*;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.model.response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.mapper.ToDoMapper;
import com.example.todolist.repository.ToDoRepository;
import com.example.todolist.repository.UserRepository;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository toDoRepository;

	@Autowired
	private UserRepository userInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private ToDoMapper toDoMapper;


	@Override
	public ToDoDTO createToDo(ToDoDTO toDoDTO) {
		ToDo toDo = new ToDo();
		toDo.setName(toDoDTO.getName());
//		toDo.setUserId(toDoDTO.getUserId());
		ToDoStatus status = toDoRepository.findByStatus(ToDoStatus.TO_DO).getStatus();
		toDo.setStatus(status);
		return toDoMapper.toDTO(toDoRepository.save(toDo));
	}

//	@Override
//	public List<ToDoDTO> createToDos(List<ToDoDTO> toDoDTOS) {
//		List<ToDo> toDos = toDoDTOS.stream().map(toDo -> ToDoMapper.getInstance().toEntity(toDo))
//				.collect(Collectors.toList());
//		List<ToDoDTO> toDoDTOList = toDos.stream()
//				.map(toDo -> ToDoMapper.getInstance().toDTO(toDoRepository.save(toDo))).collect(Collectors.toList());
//		return toDoDTOList;
//	}
	/////// GET method //////////

	@Override
	public List<ToDoDTO> getAllToDos() {
		return toDoRepository.findAll().stream().map(toDo -> ToDoMapper.getInstance().toDTO(toDo))
				.collect(Collectors.toList());
	}

//    @Override
//    public ToDoDTO getToDoById(int id) {
//        ToDo toDo = toDoRepository.findById(id).get();
//        return ToDoMapper.getInstance().toDTO(toDo);
//    }
//
//    @Override
//    public ToDoDTO getToDoByName(String name) {
//        ToDo toDo = toDoRepository.findByName(name);
//        return ToDoMapper.getInstance().toDTO(toDo);
//    }

	@Override
	public ToDoDTO updateToDo(ToDoDTO toDo) {
		ToDo existingToDo = toDoRepository.findById(toDo.getId());
		existingToDo.setName(toDo.getName());
		existingToDo.setStatus(toDo.getStatus());
		ToDo updatedToDo = toDoRepository.save(existingToDo);
		return ToDoMapper.getInstance().toDTO(updatedToDo);
	}

	@Override
	public String deleteToDo(int id) {
		toDoRepository.deleteById(id);
		return "ToDo removed: " + id;
	}
}