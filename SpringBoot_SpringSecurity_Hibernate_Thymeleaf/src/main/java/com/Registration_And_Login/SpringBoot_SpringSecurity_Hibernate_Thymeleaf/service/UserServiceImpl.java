package com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.model.Role;
import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.model.User;
import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.repository.UserRepository;
import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.web.dto.UserRegistrationDTO;

@Service
public class UserServiceImpl implements UserService
{
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) 
	{
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDTO registrationDTO) 
	{
		User user = new User(
				registrationDTO.getFirstName(),
				registrationDTO.getLastName(),
				registrationDTO.getEmail(),
				passwordEncoder.encode(registrationDTO.getPassword()),
				Arrays.asList(new Role("ROLE_USER"))
				);
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = userRepository.findByEmail(username);
		
		if (user == null)
		{
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}