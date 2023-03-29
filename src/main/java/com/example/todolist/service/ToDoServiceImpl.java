package com.example.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todolist.model.DTO.ToDoDTO;
import com.example.todolist.model.entity.ToDo;
import com.example.todolist.model.entity.User;
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

	@Override
	public String addUser(User userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return "User added";
	}

	@Override
	public List<User> addUsers(List<User> userInfos) {
		return userInfoRepository.saveAll(userInfos);
	}

	@Override
	public List<User> getUsers(List<User> userInfos) {
		return userInfoRepository.findAll();
	}

	@Override
	public User updateUser(User userInfo) {
//        User existingUser = userInfoRepository.findById(userInfo.getId()).orElse(null);
//        existingUser.setUserName(userInfo.getUserName());
//        existingUser.setPassword(userInfo.getPassword());
//        return userInfoRepository.save(existingUser);
		return null;
	}

	@Override
	public String deleteUser(int id) {
//		userInfoRepository.deleteById(id);
//		return "User deleted: " + id;
		return null;
	}

	@Override
	public ToDoDTO createToDo(ToDoDTO toDoDTO) {
		ToDo toDo = ToDoMapper.getInstance().toEntity(toDoDTO);
		return ToDoMapper.getInstance().toDTO(toDoRepository.save(toDo));
	}

	@Override
	public List<ToDoDTO> createToDos(List<ToDoDTO> toDoDTOS) {
		List<ToDo> toDos = toDoDTOS.stream().map(toDo -> ToDoMapper.getInstance().toEntity(toDo))
				.collect(Collectors.toList());
		List<ToDoDTO> toDoDTOList = toDos.stream()
				.map(toDo -> ToDoMapper.getInstance().toDTO(toDoRepository.save(toDo))).collect(Collectors.toList());
		return toDoDTOList;
	}
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