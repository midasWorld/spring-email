package com.midas.email.web;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignupRequest {
	private String email;
	private String password;
	private String name;

	public SignupRequest() {
	}

	@Builder
	public SignupRequest(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
}
