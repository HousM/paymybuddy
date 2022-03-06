package com.openclassrooms.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/registration")
	public String registration(Model model) {
		return "registration";
	}

	@PostMapping(value = "/newuser")
	public String saveUser(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		userService.saveUser(firstname, lastname, email, password);
		return "redirect:/transactions";
	}

	@GetMapping(value = "/profile")
	public String findUser(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			String email = userDetails.getUsername();
			User user = userService.findByUserEmail(email);
			model.addAttribute("userDetails", user);
		}
		return "profile";
	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}

}
