package com.krish.appsec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.krish.appsec.model.AppsecUser;
import com.krish.appsec.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUsers(Model model){
		model.addAttribute("appsecUsers", userService.getAllUsers());
		return "/users";
	}
	
	@GetMapping("/register")
	public String registerUser(@RequestParam(required = false) String user, Model model){
		AppsecUser appsecUser = new AppsecUser();
		String submitValue = "Register";
		if(user != null){
		appsecUser = userService.findOne(user);
		submitValue = "Save";
		}
		model.addAttribute("appsecUser", appsecUser);
		model.addAttribute("submitValue", submitValue);
		return "/register";
	}
	
	@PostMapping
	public String addorSaveUser(@Valid AppsecUser appsecUser, BindingResult bindingResult, Model model){
		if(!bindingResult.hasErrors()){
		userService.addUser(appsecUser);
		model.addAttribute("successMessage", "User "+appsecUser.getUserId()+" has been registered successfully.");
		}		
		model.addAttribute("submitValue", "Submit");
		return "/register";
	}
}
