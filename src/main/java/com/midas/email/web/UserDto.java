package com.midas.email.web;

import com.midas.email.domain.user.User;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDto {
	private final String email;
	private final String password;
	private final String name;

	public UserDto(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.name = user.getName();
	}
}
