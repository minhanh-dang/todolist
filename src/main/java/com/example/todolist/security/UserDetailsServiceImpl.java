package com.example.todolist.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.todolist.model.entity.User;
import com.example.todolist.model.exception.BadRequestException;
import com.example.todolist.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> optUser = repository.findByUserName(userName);

		if (!optUser.isPresent()) {
			throw new BadRequestException("User Not Found");
		}
		return UserPrincipal.build(optUser.get());
	}


}