package com.seba.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.seba.recruitment.exception.ExternalSystemException;
import com.seba.recruitment.model.UserResponse;
import com.seba.recruitment.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users/{login}")
	public @ResponseBody ResponseEntity<UserResponse> getData(@PathVariable("login") String login)
			throws InterruptedException, ExternalSystemException {
		return ResponseEntity.ok(userService.getUserResponse(login));
	}

}
