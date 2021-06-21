package com.cda.testIntractiv.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.testIntractiv.exceptions.PasswordIsNotCompliantException;
import com.cda.testIntractiv.exceptions.PasswordIsNotCorrectException;
import com.cda.testIntractiv.exceptions.UserAlreadyExistException;
import com.cda.testIntractiv.exceptions.UserInexistantException;
import com.cda.testIntractiv.model.Password;
import com.cda.testIntractiv.model.User;
import com.cda.testIntractiv.repository.IUserRepository;
import com.cda.testIntractiv.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	Password pwd = new Password();

	@Override
	public void addUser(User user) throws UserAlreadyExistException, PasswordIsNotCompliantException {

		Optional<User> userOpt = this.userRepository.findByName(user.getName());
		String password = user.getPassword();
		if (userOpt.isPresent()) {
			throw new UserAlreadyExistException();
		} else if (!pwd.testCompliance(user.getPassword()).isValid()) {
			throw new PasswordIsNotCompliantException();
		} else {
			user.setPassword(Password.getHash(password));
			this.userRepository.save(user);
		}

	}

	@Override
	public User getUser(String login) throws UserInexistantException {

		Optional<User> userOpt = this.userRepository.findByName(login);

		if (userOpt.isEmpty()) {

			throw new UserInexistantException();

		} else {

			return userOpt.get();
		}

	}

	@Override
	public boolean verify(String login, String password) throws UserInexistantException, PasswordIsNotCorrectException {

		Optional<User> userOpt = this.userRepository.findByName(login);
		System.out.println(userOpt.get().getPassword());
		System.out.println(Password.getHash(password));
		System.out.println(password);
		
		String pwd1 = userOpt.get().getPassword();
		String pwd2 = Password.getHash(password);
		if (userOpt.isEmpty()) {

			throw new UserInexistantException();

		}else {
			if (pwd1.equals(pwd2)) {

				return true;
			} else {
				throw new PasswordIsNotCorrectException();
			}
		}

		

	}

}
