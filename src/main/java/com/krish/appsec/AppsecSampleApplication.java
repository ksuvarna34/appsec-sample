package com.krish.appsec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.krish.appsec.model.AppsecUser;
import com.krish.appsec.repository.UserRepository;

@SpringBootApplication
public class AppsecSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppsecSampleApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner userRepo(UserRepository userRepository){
		return (args) -> {
			userRepository.save(new AppsecUser("ksuvarna", "Krishna", "Suvarna", "Sterling", "20166", 500.00));
			userRepository.save(new AppsecUser("rfederer", "Roger", "Federer", "Fairfax", "20330", 400.00));
			userRepository.save(new AppsecUser("mschum", "Michael", "Schumacher", "Dulles", "20163", 300.00));
			userRepository.save(new AppsecUser("tbrady", "Tom", "Brady", "Centreville", "20120", 200.00));
			userRepository.save(new AppsecUser("stend", "Sachin", "Tendulkar", "Ashburn", "20147", 100.00));
		};
	}
}
