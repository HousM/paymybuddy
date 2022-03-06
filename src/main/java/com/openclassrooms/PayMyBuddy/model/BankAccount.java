//package com.openclassrooms.PayMyBuddy.model;
//
//import java.io.Serializable;
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
//@Table(name = "bankAccount")
//public class BankAccount implements Serializable {
//
//	/**
//	 * Id of bank_account table.
//	 */
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", unique = true, nullable = false)
//	private Long id;
//
//	/**
//	 * User whose bank account belong to.
//	 */
//	@OneToOne
//	@JoinColumn(name = "user_id", nullable = false)
//	private User owner;
//
//	/**
//	 * IBAN code of bank account.
//	 */
//	@Column(name = "iban")
//	private String iban;
//
//	/**
//	 * BIC code of bank account.
//	 */
//	@Column(name = "bic")
//	private String bic;
//
//	/**
//	 * Constructor of class BankAccount. Initialize owner, iban and bic.
//	 * 
//	 */
//	public BankAccount(User owner, String iban, String bic) {
//		super();
//		this.owner = owner;
//		this.iban = iban;
//		this.bic = bic;
//	}
//}
