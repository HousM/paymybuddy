package com.openclassrooms.PayMyBuddy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.openclassrooms.PayMyBuddy.constant.TransactionType;

@Entity
@DynamicUpdate
@Table(name = "transaction")
public class Transaction implements Serializable {

	/**
	 * Id of transaction table.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	/**
	 * The type of transaction.
	 */

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TransactionType type;

	/**
	 * Buddy account of sender
	 */
	@ManyToOne
	@JoinColumn(name = "buddy_account_owner_id")
	private BuddyAccount buddyOwner;

	/**
	 * Buddy account of receiver
	 */
	@ManyToOne
	@JoinColumn(name = "buddy_account_receiver_id")
	private BuddyAccount buddyReceiver;

	/**
	 * Bank account of sender
	 */
	@ManyToOne
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;

	/**
	 * Email of the buddy.
	 */
	@Column(name = "date", nullable = false)
	private LocalDate date;

	/**
	 * Description of transaction.
	 */
	@Column(name = "description", nullable = false)
	private String description;

	/**
	 * Amount of transaction.
	 */
	@Column(name = "amount")
	private BigDecimal amount;

	/**
	 * Fee calculated of transaction.
	 */
	@Column(name = "fee")
	private BigDecimal fee;

	private String email;

	/**
	 * Constructor of class TransactionDTO. Initialize type, buddyEmail, address,
	 * date, description, amount and fee.
	 * 
	 */
	public Transaction(TransactionType type, BuddyAccount buddyOwner, BuddyAccount buddyReceiver,
			LocalDate date, String description, BigDecimal amount, BigDecimal fee) {
		super();
		this.type = type;
		this.buddyOwner = buddyOwner;
		this.buddyReceiver = buddyReceiver;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.fee = fee;
	}

	/**
	 * Constructor of class TransactionDTO. Initialize type, buddyEmail, address,
	 * date, description, amount and fee.
	 * 
	 */
	public Transaction(TransactionType type, BuddyAccount buddyOwner, BankAccount bankAccount,
			LocalDate date, String description, BigDecimal amount, BigDecimal fee) {
		super();
		this.type = type;
		this.buddyOwner = buddyOwner;
		this.bankAccount = bankAccount;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.fee = fee;
	}

	public Transaction(TransactionType type, String email,
			LocalDate date, String description, BigDecimal amount, BigDecimal fee) {

		this.type = type;
		this.email = email;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.fee = fee;
	}
}
