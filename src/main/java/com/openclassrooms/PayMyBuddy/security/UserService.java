package com.openclassrooms.PayMyBuddy.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	/**
	 * MyUserDetailsService logger.
	 */
	private static Logger LOGGER = LogManager.getLogger(UserService.class);

	/**
	 * UserRepository instance.
	 */
	private UserRepository userRepository;

	/**
	 * Constructor of class MyUserDetailsService. Initialize userRepository.
	 */
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LOGGER.debug("Inside MyUserDetailsService.loadUserByUsername for username : " + email);

		User user = userRepository.findByEmail(email);

		if (user == null) {
			LOGGER.error("Invalid username or password");
			throw new UsernameNotFoundException("Invalid username or password");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getEmail(),
				grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				grantedAuthorities);
	}
}
