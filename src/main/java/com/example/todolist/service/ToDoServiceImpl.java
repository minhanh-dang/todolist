package com.example.todolist.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.todolist.model.DTO.UserDto;
import com.example.todolist.model.entity.*;
import com.example.todolist.model.mapper.UserMapper;
import com.example.todolist.model.response.ToDoResponse;
import com.example.todolist.model.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.mapper.ToDoMapper;
import com.example.todolist.repository.ToDoRepository;
import com.example.todolist.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

	private ToDoRepository toDoRepository;

	private UserRepository userRepository;

	private ToDoMapper toDoMapper;

	private UserMapper userMapper;

	@Override
	public ToDoDTO getToDoById(Long id){
		ToDo toDo = toDoRepository.findById(id).get();

		ToDoDTO toDoDTO = toDoMapper.toDTO(toDo);

		return toDoDTO;
	}

	// get a ToDoDTO, initialize a new ToDo with name, status; save it in repo and the convert it to DTO again.
	@Override
	public ToDoDTO createToDo(ToDoDTO toDoDTO) {
		ToDo toDo = new ToDo();
		toDo.setName(toDoDTO.getName());
//		ToDoStatus status = toDoRepository.findByStatus(ToDoStatus.TO_DO).getStatus();
//		toDo.setStatus(status);
		toDo.setStatus(toDoDTO.getStatus());
		return toDoMapper.toDTO(toDoRepository.save(toDo));
	}

	@Override
	public ToDoDTO createToDo(Long id, ToDoDTO toDoDTO) {

		User user = userRepository.findById(id).get();
		ToDo toDo = new ToDo();
		toDo.setName(toDoDTO.getName());
		toDo.setUser(user);
		toDo.setStatus(toDoDTO.getStatus());
		ToDo savedToDo = toDoRepository.save(toDo);

		return toDoMapper.toDTO(savedToDo);
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
		List<ToDoDTO> toDoDTOS = toDoRepository.findAll().stream().map(todo -> ToDoMapper.getInstance().toDTO(todo))
				.collect(Collectors.toList());
//		List<ToDoResponse> toDoResponses = toDoDTOS.stream().map(todo -> ToDoMapper.getInstance().toResponse(todo))
//				.collect(Collectors.toList());
		return toDoDTOS;
	}

	@Override
	public List<ToDoDTO> getUserToDo(Long id){
		List<ToDoDTO> toDoDTOS = toDoRepository.findAll().stream().map(todo -> ToDoMapper.getInstance().toDTO(todo))
				.collect(Collectors.toList());
		List<UserDto> userDtos = userRepository.findAll().stream().map(user -> userMapper.toDto(user))
				.collect(Collectors.toList());
		List<ToDoDTO> result = toDoDTOS.stream().filter(todo -> userDtos.stream()
				.anyMatch(user -> user.getId().equals(id))).collect(Collectors.toList());
//		List<ToDoResponse> result = toDoResponses.stream().map(todo -> ToDoMapper.getInstance().toResponse(todo))
//				.collect(Collectors.toList());
		return result;
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
	public ToDoDTO updateToDo(Long id, ToDoDTO toDo) {
		ToDo existingToDo = toDoRepository.findById(id).get();
		existingToDo.setName(toDo.getName());
		existingToDo.setStatus(toDo.getStatus());
		ToDo updatedToDo = toDoRepository.save(existingToDo);
		return ToDoMapper.getInstance().toDTO(updatedToDo);
	}

	@Override
	public String deleteToDo(Long id) {
		toDoRepository.deleteById(id);
//		return "ToDo removed: " + id;
		return null;
	}
}