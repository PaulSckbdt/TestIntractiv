package com.cda.testIntractiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cda.testIntractiv.exceptions.PasswordIsNotCompliantException;
import com.cda.testIntractiv.exceptions.PasswordIsNotCorrectException;
import com.cda.testIntractiv.exceptions.UserAlreadyExistException;
import com.cda.testIntractiv.exceptions.UserInexistantException;
import com.cda.testIntractiv.model.User;
import com.cda.testIntractiv.services.IUserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

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

	@GetMapping("/api/users/{login}")
	@Operation(summary = "get user by login")
	public ResponseEntity<User> getUser(@PathVariable String login) {

		User user;
		try {
			user = this.userService.getUser(login);
		} catch (UserInexistantException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.ok(user);

	}

	@PostMapping(path = "/api/users/{login}/verify", consumes = "application/json")
	@Operation(summary = "Verify login/password")
	public ResponseEntity<String> verifyUser(@PathVariable String login, @RequestBody String password) {

		try {
			this.userService.verify(login, password);
		} catch (UserInexistantException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
		} catch (PasswordIsNotCorrectException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid password");
		}
		return ResponseEntity.status(HttpStatus.OK).body("password is ok");

	}

}
