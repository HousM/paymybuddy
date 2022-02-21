package com.openclassrooms.PayMyBuddy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.PayMyBuddy.dto.ConnectionDTO;
import com.openclassrooms.PayMyBuddy.dto.ContactsDTO;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.Result;
import com.openclassrooms.PayMyBuddy.service.IUserService;
import com.openclassrooms.PayMyBuddy.util.LoginEmailRetriever;

@Controller
public class UserController {
	/**
	 * UserController logger.
	 */
	private static Logger LOGGER = LogManager.getLogger(UserController.class);

	/**
	 * IUserService's implement class reference.
	 */

	@Autowired
	private IUserService userService;
	/**
	 * LoginEmailRetriever instance.
	 */
	private LoginEmailRetriever loginEmailRetriever;

	/**
	 * Registers a new user.
	 */
	@PostMapping("/registration")
	public ResponseEntity<Result> registerUser(@Valid @RequestBody User user) {
		LOGGER.debug("Registration request with username {}", user.getEmail());

		Result userSaved = userService.registerUser(user);

		LOGGER.info("User registration request - SUCCESS");
		return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
	}

	/**
	 * Adds a new connection.
	 * 
	 */
	@PostMapping("/contact")
	public ResponseEntity<ContactsDTO> addConnection(@Valid @RequestBody ConnectionDTO connectionDTO,
			HttpServletRequest request) {
		LOGGER.debug("Add connection request with buddy email {}", connectionDTO.getBuddyEmail());

		String ownerEmail = loginEmailRetriever.getUsername(request);

		ContactsDTO contacts = userService.addConnection(ownerEmail, connectionDTO.getBuddyEmail());

		LOGGER.info("Add connection request - SUCCESS");
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}
}
