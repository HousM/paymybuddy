package com.openclassrooms.PayMyBuddy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDateTime date;

	/**
	 * Description of transaction.
	 */
	@Column(name = "description", nullable = false)
	private String description;

	/**
	 * Amount of transaction.
	 */
	@Column(name = "amount")
	private float amount;

	/**
	 * Fee calculated of transaction.
	 */
	@Column(name = "fee")
	private float fee;

	private String email;
	
	private User user;

	/**
	 * Constructor of class TransactionDTO. Initialize type, buddyEmail, address,
	 * date, description, amount and fee.
	 * 
	 */
	public Transaction(User user, TransactionType type, BuddyAccount buddyOwner, BuddyAccount buddyReceiver,
			LocalDateTime date, String description, float amount, float fee) {
		super();
		this.user = user;
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
	public Transaction() {};
	
	public Transaction(TransactionType type,User user, BuddyAccount buddyAccountreciver, BuddyAccount buddyAccountowner,
			LocalDateTime date, String description, float amount, float fee) {
		super();
		this.user = user;
		this.type = type;
		this.buddyOwner = buddyAccountreciver;
		this.buddyOwner = buddyAccountowner;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.fee = fee;
	}

	public Transaction(User user, TransactionType type, String email,
			LocalDateTime date, String description, float amount, float fee) {
		this.user = user;
		this.type = type;
		this.email = email;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.fee = fee;
	}
	
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public float getAmount() {
        return amount;
    }
    
    public BuddyAccount setBuddyOwner(BuddyAccount buddyOwner) {
        return this.buddyOwner = buddyOwner;
    }
    
    public BuddyAccount getBuddyOwner() {
        return buddyOwner;
    }
    
    public BuddyAccount setBuddyReciver(BuddyAccount buddyReceiver) {
        return this.buddyReceiver= buddyReceiver ;
    }
    
    public BuddyAccount getBuddyReciver() {
        return buddyReceiver;
    }
    
    public LocalDateTime setDate(LocalDateTime date) {
        return this.date = date;
    }
    
   public float setFee(float fee) {
        return this.fee = fee;
    }
   
   public User setUser(User user) {
       return this.user = user;
   }
   
   public User getUser() {
       return  user;
   }
   
   
   public TransactionType setType(TransactionType type) {
       return this.type = type;
   }
   
   public TransactionType getType() {
       return type;
   }


    
    
}
