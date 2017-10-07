package com.krish.appsec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.krish.appsec.model.AppsecUser;

public interface UserRepository extends CrudRepository<AppsecUser, String>{
	List<AppsecUser> findByLastName(String lastName);
	}
