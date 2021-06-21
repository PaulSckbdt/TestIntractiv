package com.cda.testIntractiv.services;

import com.cda.testIntractiv.exceptions.PasswordIsNotCompliantException;
import com.cda.testIntractiv.exceptions.UserAlreadyExistException;
import com.cda.testIntractiv.model.User;

public interface IUserService {

	void addUser(User user) throws UserAlreadyExistException, PasswordIsNotCompliantException;

}
