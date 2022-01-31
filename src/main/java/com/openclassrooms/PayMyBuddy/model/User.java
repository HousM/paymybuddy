package com.openclassrooms.PayMyBuddy.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id of user table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * First name of the user.
	 */
	@Column(name = "first_name")
	private String firstName;

	/**
	 * Last name of the user.
	 */
	@Column(name = "last_name")
	private String lastName;

	/**
	 * Email of the user.
	 */
	@Column(name = "email", unique = true)
	private String email;

	/**
	 * Password of the user.
	 */
	@Column(name = "password")
	private String password;

	/**
	 * Phone number of the user.
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * Buddy account of the user.
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
	private BuddyAccount buddyAccount;

	/**
	 * Bank account of the user.
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
	private BankAccount bankAccount;

	/**
	 * Contacts of the user.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "connect", joinColumns = @JoinColumn(name = "owner_id"), inverseJoinColumns = @JoinColumn(name = "buddy_id"))
	private Collection<User> contacts;

	/**
	 * Constructor of class User. Initialize firstName, lastName, email, password
	 * and phone.
	 * 
	 */
	public User(String firstName, String lastName, String email, String password,
			String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
}
