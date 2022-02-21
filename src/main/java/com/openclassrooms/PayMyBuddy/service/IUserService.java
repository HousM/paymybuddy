package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.dto.ContactsDTO;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.Result;

public interface IUserService {
	/**
	 * Registers a new User in database.
	 * 
	 */
	Result registerUser(User user);

	/**
	 * Retrieves an User based on the given email.
	 * 
	 */
	User getUserByEmail(String email);

	/**
	 * Adds a buddy to contacts.
	 * 
	 */
	ContactsDTO addConnection(String ownerEmail, String buddyEmail);

	User getUserId(int userId);
}
