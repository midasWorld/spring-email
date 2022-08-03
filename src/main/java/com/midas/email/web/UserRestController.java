package com.midas.email.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.email.domain.user.User;
import com.midas.email.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserRestController {

	private final UserService userService;

	@PostMapping("/signup")
	public UserDto signup(@RequestBody SignupRequest request) {
		User user = userService.signup(request.getEmail(), request.getPassword(), request.getName());

		return new UserDto(user);
	}

	@PostMapping("/signin")
	public UserDto signin(@RequestBody SigninRequest request) {
		User user = userService.signin(request.getEmail(), request.getPassword());

		return new UserDto(user);
	}

	@GetMapping("/confirm-email")
	public String confirmEmail(EmailAuthRequest request) {
		userService.confirmEmail(request.getEmail(), request.getAuthToken());
		return "인증에 성공하였습니다.";
	}
}
