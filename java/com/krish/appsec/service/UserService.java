package com.krish.appsec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krish.appsec.model.AppsecUser;
import com.krish.appsec.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public Iterable<AppsecUser> getAllUsers(){
		return userRepository.findAll();
	}
	
	public AppsecUser findOne(String id) {
		return userRepository.findOne(id);
	}
	public void addUser(AppsecUser user) {
		userRepository.save(user);
	}
}
