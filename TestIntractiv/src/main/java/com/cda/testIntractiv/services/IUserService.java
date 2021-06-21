package com.cda.testIntractiv.services;

import com.cda.testIntractiv.exceptions.PasswordIsNotCompliantException;
import com.cda.testIntractiv.exceptions.PasswordIsNotCorrectException;
import com.cda.testIntractiv.exceptions.UserAlreadyExistException;
import com.cda.testIntractiv.exceptions.UserInexistantException;
import com.cda.testIntractiv.model.User;

public interface IUserService {

	void addUser(User user) throws UserAlreadyExistException, PasswordIsNotCompliantException;

	User getUser(String login) throws UserInexistantException;


	boolean verify(String login, String password) throws UserInexistantException, PasswordIsNotCorrectException;

}
