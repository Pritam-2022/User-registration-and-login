package com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Registration_And_Login.SpringBoot_SpringSecurity_Hibernate_Thymeleaf.service.UserService;

//@SuppressWarnings("deprecation")
@Configuration 
@EnableWebSecurity 
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		// super.configure(http);
		http.authorizeRequests().antMatchers(
				"/registration**",
				"/js/**",
				"/css/**",
				"/img/**"
			).permitAll().anyRequest().authenticated().and().formLogin().
		loginPage("/login").permitAll().and().logout().
		invalidateHttpSession(true).clearAuthentication(true).
		logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
		logoutSuccessUrl("/login?logout").permitAll();
	}
}
