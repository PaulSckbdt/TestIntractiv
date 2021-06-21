package com.cda.testIntractiv.servicesImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.testIntractiv.exceptions.PasswordIsNotCompliantException;
import com.cda.testIntractiv.exceptions.UserAlreadyExistException;
import com.cda.testIntractiv.model.Password;
import com.cda.testIntractiv.model.User;
import com.cda.testIntractiv.repository.IUserRepository;
import com.cda.testIntractiv.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

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

}
