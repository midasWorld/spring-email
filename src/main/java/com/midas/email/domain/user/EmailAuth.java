package com.midas.email.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EmailAuth {

	private static final Long MAX_EXPIRY_SECOND = 5L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String authToken;
	private Boolean expired;
	private LocalDateTime expireDate;

	@Builder
	public EmailAuth(String email, String authToken, Boolean expired) {
		this.email = email;
		this.authToken = authToken;
		this.expired = expired;
		this.expireDate = LocalDateTime.now().plusMinutes(MAX_EXPIRY_SECOND);
	}

	public void useToken() {
		this.expired = true;
	}
}
