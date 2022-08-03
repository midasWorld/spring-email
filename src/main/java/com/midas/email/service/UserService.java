package com.midas.email.service;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.email.domain.user.EmailAuth;
import com.midas.email.domain.user.EmailAuthRepository;
import com.midas.email.domain.user.User;
import com.midas.email.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final EmailAuthRepository emailAuthRepository;
	private final EmailService emailService;

	@Transactional
	public User signup(String email, String password, String name) {
		userRepository.findByEmail(email)
			.ifPresent(exists -> {
				throw new IllegalArgumentException("해당 이메일은 이미 존재합니다.");
			});

		EmailAuth emailAuth = EmailAuth.builder()
			.email(email)
			.authToken(UUID.randomUUID().toString())
			.expired(false)
			.build();

		User user = User.builder()
			.email(email)
			.password(password)
			.name(name)
			.build();

		userRepository.save(user);

		emailService.send(email, emailAuth.getAuthToken());

		return user;
	}

	public User signin(String email, String password) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException("User not found with email=" + email));
		if (!user.getPassword().equals(password)) {
			throw new IllegalArgumentException("Password not matched=" + password);
		}
		if (!user.getEmailAuth()) {
			throw new IllegalArgumentException("Email has not been verified.");
		}
		return user;
	}

	@Transactional
	public void confirmEmail(String email, String authToken) {
		EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(email, authToken, now())
			.orElseThrow(() -> new IllegalArgumentException("Email token not found with email=" + email));
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException("User not found with email=" + email));
		emailAuth.useToken();
		user.emailVerifiedSuccess();
	}
}
