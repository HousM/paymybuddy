package com.openclassrooms.PayMyBuddy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static Logger LOGGER = LogManager.getLogger(UserService.class);

	private UserRepository userRepository;

	public UserService() {

	}

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private BCryptPasswordEncoder passwordEncoder;

	public User saveUser(String firstname, String lastname, String email, String password) {
		User user = new User(firstname, lastname, email, password, null);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		String pwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(pwd);

		User userFound = userRepository.findByEmail(user.getEmail());

		if (userFound != null) {
			throw new UsernameNotFoundException("Registration failed. The email provided may be registered " +
					"already");
		}

		User user1 = userRepository.save(user);

		return new User(user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword(),
				user1.getPhone());

	}

	public User findByUserEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User getUserByEmail(String email) {
		LOGGER.debug("Inside UserService.getUserByEmail for email : " + email);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("No user registered with this email");
		}

		return user;
	}

	public User getUserId(int userId) {
		LOGGER.debug("Inside UserService.getUserByEmail for email : " + userId);
		User user = userRepository.getUserById(userId);

		if (user == null) {
			throw new UsernameNotFoundException("No user registered with this email");
		}

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}

}
