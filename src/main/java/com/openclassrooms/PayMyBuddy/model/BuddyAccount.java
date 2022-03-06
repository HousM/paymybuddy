//package com.openclassrooms.PayMyBuddy.model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.DynamicUpdate;
//
//@Entity
//@DynamicUpdate
//@Table(name = "buddyAccount")
//public class BuddyAccount implements Serializable {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * Id of buddy_account table.
//	 */
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", unique = true, nullable = false)
//	private Long id;
//
//	/**
//	 * User whose this buddy account belongs to.
//	 */
//	@OneToOne
//	@JoinColumn(name = "user_id", nullable = false)
//	private User owner;
//
//	/**
//	 * The credit balance of the Buddy account.
//	 */
//	@Column(name = "balance", nullable = false)
//	private BigDecimal balance;
//
//	/**
//	 * Constructor of class BuddyAccount. Initialize owner and balance.
//	 *
//	 * @param owner   user whose buddy account belong to
//	 * @param balance the credit balance of Buddy account
//	 */
//	public BuddyAccount(User owner, final BigDecimal balance) {
//		super();
//		this.owner = owner;
//		this.balance = balance;
//	}
//}
