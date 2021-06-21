package com.cda.testIntractiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.testIntractiv.exceptions.PasswordIsNotCompliantException;
import com.cda.testIntractiv.exceptions.UserAlreadyExistException;
import com.cda.testIntractiv.model.User;
import com.cda.testIntractiv.services.IUserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(description = "Create Update Verify for User Object")
@RestController
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("/api/users/")
	@Operation(summary = "Create a new user")
	public ResponseEntity<String> createUser(@RequestBody User user) {

		try {
			this.userService.addUser(user);
		} catch (UserAlreadyExistException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("user already exists");
		} catch (PasswordIsNotCompliantException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password is not Compliant");

		}

		return ResponseEntity.status(HttpStatus.CREATED).body("user created");

	}

}
