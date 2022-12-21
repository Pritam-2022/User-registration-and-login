package com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController 
{
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
}
