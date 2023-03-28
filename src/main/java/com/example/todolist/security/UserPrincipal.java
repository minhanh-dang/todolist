package com.example.todolist.security;

import com.example.todolist.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String email;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private boolean lock;

	public Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String name, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
		return new UserPrincipal(user.getId(), user.getUserName(), user.getPassword(), authorities);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getUsername() {
		return name;
	}

<<<<<<< HEAD


//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
=======
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
>>>>>>> 37c92f4d0fa3a28715cb33e3820124eab1a6fa72

	@Override
	public String getPassword() {
		return password;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if (this.lock == true) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;

	}

	@Override
	public boolean isEnabled() {
		if (this.lock == true) {
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrincipal user = (UserPrincipal) o;
		return Objects.equals(id, user.id);
	}
}
