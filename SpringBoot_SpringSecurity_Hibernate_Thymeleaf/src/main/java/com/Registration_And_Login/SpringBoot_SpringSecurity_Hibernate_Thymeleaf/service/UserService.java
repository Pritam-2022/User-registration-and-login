package com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.model.User;
import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.web.dto.UserRegistrationDTO;

public interface UserService extends UserDetailsService
{
	User save(UserRegistrationDTO registrationDTO);
}
