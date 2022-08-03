package com.midas.email.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.midas.email.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String password;
	private String name;

	private String provider;
	private Boolean emailAuth;

	@Builder
	public User(String email, String password, String name, String provider, Boolean emailAuth) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.provider = provider;
		this.emailAuth = emailAuth != null && emailAuth;
	}

	public void emailVerifiedSuccess() {
		this.emailAuth = true;
	}
}
