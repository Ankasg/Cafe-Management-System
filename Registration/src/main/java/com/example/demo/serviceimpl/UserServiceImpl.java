package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User Register(User user) {
		return userRepository.save(user);
	}

	@Override
	public User login(User user) {
		User foundUser = userRepository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
		if (foundUser == null) {
			System.out.println("Login failed for email: " + user.getEmailId());
		}
		return foundUser;
	}
}
