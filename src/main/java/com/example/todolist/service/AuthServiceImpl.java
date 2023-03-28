package com.example.todolist.service;

import com.example.todolist.model.exception.BadRequestException;
import com.example.todolist.model.request.LoginRequest;
import com.example.todolist.model.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	@Override
	public AuthenticationResponse authenticate(LoginRequest request) {
		Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));

		if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			String jwt = jwtService.generateToken(authentication);
			return AuthenticationResponse.builder().token(jwt).expiredAt(expiredAt).build();
		} else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			String jwt = jwtService.generateToken(authentication);
			return AuthenticationResponse.builder().token(jwt).expiredAt(expiredAt).build();
		} else {
			throw new BadRequestException("Username or Password is incorrect!");
		}
	}
}
