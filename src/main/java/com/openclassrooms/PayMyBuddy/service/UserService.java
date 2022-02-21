package com.openclassrooms.PayMyBuddy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.Result;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;

@Service
public abstract class UserService implements IUserService {

	private static Logger LOGGER = LogManager.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Result registerUser(User user) {
		LOGGER.debug("Inside UserService.registerUser for username : " + user.getEmail());
		User userFound = userRepository.getUser(user.getEmail());

		if (userFound != null) {
			throw new UsernameNotFoundException("Registration failed. The email provided may be registered " +
					"already");
		}
		String password = passwordEncoder.encode(user.getPassword());
		User userToSave = new User(user.getFirstName(), user.getLastName(), user.getEmail(),
				password, user.getPhone());

		Result user1 = userRepository.saveUser(userToSave);
//		buddyAccountService.saveBuddyAccount(new BuddyAccount(user1, BigDecimal.ZERO));

//		return new User(user1.getFirstName(), user1.getLastName(), user1.getEmail(), user1.getPassword(),
//				user1.getPhone());
		return user1;
	}

	@Override
	public User getUserByEmail(String email) {
		LOGGER.debug("Inside UserService.getUserByEmail for email : " + email);
		User user = userRepository.getUser(email);

		if (user == null) {
			throw new UsernameNotFoundException("No user registered with this email");
		}

		return user;
	}

	@Override
	public User getUserId(int userId) {
		LOGGER.debug("Inside UserService.getUserByEmail for email : " + userId);
		User user = userRepository.getUserById(userId);

		if (user == null) {
			throw new UsernameNotFoundException("No user registered with this email");
		}

		return user;
	}
//	@Override
//	public ContactsDTO addConnection(String ownerEmail, String buddyEmail) {
//		LOGGER.debug("Inside UserService.addConnection");
//		User owner = userRepository.getUser(ownerEmail);
//		User buddyToAdd = userRepository.getUser(buddyEmail);
//
//		if (buddyToAdd == null) {
//			throw new UsernameNotFoundException("No buddy registered with this email");
//		}
//		if (owner.getContacts().contains(buddyToAdd)) {
//			throw new AopInvocationException("This connection is already in your contacts");
//		}
//		owner.getContacts().add(buddyToAdd);
//		Result userSaved = userRepository.saveUser(owner);
//
//		ContactsDTO contactList = new ContactsDTO();
//		for (User user : userSaved.getContacts()) {
//			contactList.getContacts().add(user.getEmail());
//		}
//
//		return contactList;
//	}

}
