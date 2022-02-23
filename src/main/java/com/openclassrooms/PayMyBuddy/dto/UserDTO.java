package com.openclassrooms.PayMyBuddy.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.sun.istack.NotNull;

@Entity
@DynamicUpdate
@Table(name = "userDTO")
public class UserDTO {
	/**
	 * First name of the user.
	 */
	@NotNull
	private String firstName;

	/**
	 * Last name of the user.
	 */
	@NotNull
	private String lastName;

	/**
	 * Email of the user.
	 */
	@NotNull
	private String email;

	/**
	 * Password of the user.
	 */
	@NotNull
	private String password;

	/**
	 * Phone number of the user.
	 */
	@NotNull
	private String phone;
}
