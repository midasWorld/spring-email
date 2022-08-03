package com.midas.email.web;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SigninRequest {
	private String email;
	private String password;

	public SigninRequest() {
	}

	@Builder
	public SigninRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
