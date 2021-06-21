package com.cda.testIntractiv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cda.testIntractiv.exceptions.PasswordIsNotCompliantException;
import com.cda.testIntractiv.exceptions.UserAlreadyExistException;
import com.cda.testIntractiv.model.User;
import com.cda.testIntractiv.repository.IUserRepository;
import com.cda.testIntractiv.servicesImpl.UserServiceImpl;

@SpringBootTest
class TestUserCrud {

	@Autowired
	IUserRepository userRepository;

	@Autowired
	UserServiceImpl userService;

	@BeforeEach
	void vider() {

		this.userRepository.deleteAll();
	}

	@Order(1)
	@Test
	void testCreatePwdException() throws UserAlreadyExistException, PasswordIsNotCompliantException {

		User user = new User(1, "paul", "0684092574", "Dza123A");

		assertThrows(PasswordIsNotCompliantException.class, () -> {
			this.userService.addUser(user);
		});
	}

	@Order(1)
	@Test
	void testCreateUserAlreadyExist() throws UserAlreadyExistException, PasswordIsNotCompliantException {

		User user = new User(1, "paul", "0684092574", "D@Fsza123A");
		this.userService.addUser(user);

		assertThrows(UserAlreadyExistException.class, () -> {
			this.userService.addUser(user);
		});
	}

}
