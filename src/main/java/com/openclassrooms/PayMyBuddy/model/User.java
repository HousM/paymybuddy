package com.openclassrooms.PayMyBuddy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.hibernate.annotations.DynamicUpdate;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
@DynamicUpdate
@Table(name = "user")
public class User implements Serializable, UserDetails {

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
	private int id;

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

    private List<User> usersList = new ArrayList<>();


	/**
	 * Constructor of class User. Initialize firstName, lastName, email, password
	 * and phone.
	 * 
	 */
	public User(int id, String firstName, String lastName, String email, String password,
			String phone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	
	public User(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public BankAccount getBankAccount () {
		return bankAccount;
	}
	
	public BankAccount setBankAccount (BankAccount bankAccount) {
		return this.bankAccount=bankAccount;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public int getId() {
	return id;
	}
	
	public int setId(int id) {
	return this.id=id;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone() {

	}

	public String getEmail() {
		return email;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPhone() {
		return phone;
	}
	
    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }
}
