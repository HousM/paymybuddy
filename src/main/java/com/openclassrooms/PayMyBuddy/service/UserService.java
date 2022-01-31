package com.openclassrooms.PayMyBuddy.service;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.dto.ContactsDTO;
import com.openclassrooms.PayMyBuddy.model.BuddyAccount;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public class UserService implements IUserService {

	private static Logger LOGGER = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	private IBuddyAccountService buddyAccountService;

	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		LOGGER.debug("Inside UserService.registerUser for username : " + user.getEmail());
		User userFound = userRepository.findByEmail(user.getEmail());

		if (userFound != null) {
			throw new UsernameNotFoundException("Registration failed. The email provided may be registered " +
					"already");
		}
		String password = passwordEncoder.encode(user.getPassword());
		User userToSave = new User(user.getFirstName(), user.getLastName(), user.getEmail(),
				password, user.getPhone());

		User user1 = userRepository.save(userToSave);
		buddyAccountService.saveBuddyAccount(new BuddyAccount(user1, BigDecimal.ZERO));

		return new User(user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword(),
				user1.getPhone());
	}

	@Override
	public User getUserByEmail(String email) {
		LOGGER.debug("Inside UserService.getUserByEmail for email : " + email);
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("No user registered with this email");
		}

		return user;
	}

	@Override
	public ContactsDTO addConnection(String ownerEmail, String buddyEmail) {
		LOGGER.debug("Inside UserService.addConnection");
		User owner = userRepository.findByEmail(ownerEmail);
		User buddyToAdd = userRepository.findByEmail(buddyEmail);

		if (buddyToAdd == null) {
			throw new UsernameNotFoundException("No buddy registered with this email");
		}
		if (owner.getContacts().contains(buddyToAdd)) {
			throw new AopInvocationException("This connection is already in your contacts");
		}
		owner.getContacts().add(buddyToAdd);
		User userSaved = userRepository.save(owner);

		ContactsDTO contactList = new ContactsDTO();
		for (User user : userSaved.getContacts()) {
			contactList.getContacts().add(user.getEmail());
		}

		return contactList;
	}
}
