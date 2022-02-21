package com.openclassrooms.PayMyBuddy.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class UserDTO {
	/**
	 * First name of the user.
	 */
	@NotNull
	@NotEmpty(message = "First name is required")
	@Length(max = 70)
	private String firstName;

	/**
	 * Last name of the user.
	 */
	@NotNull
	@NotEmpty(message = "Last name is required")
	@Length(max = 70)
	private String lastName;

	/**
	 * Email of the user.
	 */
	@NotNull
	@NotEmpty(message = "Email address is required")
	@Length(min = 3, message = "Please enter a valid email address")
	private String email;

	/**
	 * Password of the user.
	 */
	@NotNull
	@NotEmpty(message = "Password is required")
	@Length(min = 5, message = "The Password you provided must have at least 5 characters")
	private String password;

	/**
	 * Phone number of the user.
	 */
	@NotNull
	@NotEmpty(message = "Phone number is required")
	@Length(max = 10, message = "Please enter a valid phone number")
	private String phone;
}
