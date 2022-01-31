package com.openclassrooms.PayMyBuddy.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * UserDetailsService instance.
	 */
	private final UserService userService;

	/**
	 * ObjectMapper instance.
	 */
	private final ObjectMapper objectMapper;

	/**
	 * Constructor of class SecurityConfiguration. Initialize userDetailsService and
	 * objectMapper.
	 * 
	 */
	public SecurityConfig(UserService userService, ObjectMapper objectMapper) {
		this.userService = userService;
		this.objectMapper = objectMapper;
	}

	/**
	 * Creates an instance of BCryptPasswordEncoder in order to encrypt the
	 * password.
	 * 
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configure the AuthenticationManagerBuilder to use the password encoder.
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Allows configuring web based security for specific httprequests.
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.requestCache().disable()
				.authorizeRequests()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/login", "/registration").permitAll()
				.anyRequest()
				.authenticated()
				.and().formLogin()
				.loginProcessingUrl("/login") // the URL on which the clients should post the login information
				.successHandler(this::loginSuccessHandler)
				.failureHandler(this::loginFailureHandler)
				.and()
				.logout()
				.logoutUrl("/logout") // the URL on which the clients should post if they want to logout
				.logoutSuccessHandler(this::logoutSuccessHandler)
				.invalidateHttpSession(true)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(this::authenticationEntryPointHandler);
	}

	/**
	 * Handles login success.
	 */
	private void loginSuccessHandler(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), "You are now logged in to Pay my Buddy!");
	}

	/**
	 * Handles login failure.
	 * 
	 */
	private void loginFailureHandler(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		objectMapper.writeValue(response.getWriter(), "The email or password you entered is incorrect.");
	}

	/**
	 * Handles logout success.
	 * 
	 */
	private void logoutSuccessHandler(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), "You have successfully logged out!");
	}

	/**
	 * Handles authenticationEntryPoint.
	 * 
	 */
	private void authenticationEntryPointHandler(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException {
		response.setStatus(HttpStatus.FORBIDDEN.value());
		objectMapper.writeValue(response.getWriter(), "You have to logged in first!");
	}
}
