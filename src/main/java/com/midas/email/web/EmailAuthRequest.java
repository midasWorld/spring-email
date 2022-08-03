package com.midas.email.web;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class EmailAuthRequest {
	private String email;
	private String authToken;

	public EmailAuthRequest(String email, String authToken) {
		this.email = email;
		this.authToken = authToken;
	}
}
