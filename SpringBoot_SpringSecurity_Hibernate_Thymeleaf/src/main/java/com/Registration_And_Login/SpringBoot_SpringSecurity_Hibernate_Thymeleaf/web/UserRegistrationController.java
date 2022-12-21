package com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.service.UserService;
import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.web.dto.UserRegistrationDTO;

@Controller 
@RequestMapping("/registration")
public class UserRegistrationController 
{
	public UserService userService;

	public UserRegistrationController(UserService userService) 
	{
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDTO userRegistrationDTO()
	{
		return new UserRegistrationDTO();
	}
	
	@GetMapping
	public String showRegistrationForm()
	{
		return "registration";
	}
	
	/*
	@GetMapping
	public String showRegistrationForm(Model model)
	{
		model.addAttribute("user", new UserRegistrationDTO());
		return "registration";
	}
	*/
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO)
	{
		userService.save(registrationDTO);
		return "redirect:/registration?success";
	}
}
