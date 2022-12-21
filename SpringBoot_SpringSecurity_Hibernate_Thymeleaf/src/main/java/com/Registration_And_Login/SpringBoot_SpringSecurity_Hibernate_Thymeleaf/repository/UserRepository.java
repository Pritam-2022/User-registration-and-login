package com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	User findByEmail(String email);
}
