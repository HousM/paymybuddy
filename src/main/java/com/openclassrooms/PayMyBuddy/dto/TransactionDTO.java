package com.openclassrooms.PayMyBuddy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.openclassrooms.PayMyBuddy.constant.TransactionType;
import com.openclassrooms.PayMyBuddy.model.BuddyAccount;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class TransactionDTO {
	/**
	 * The type of transaction.
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TransactionType type;
	/**
	 * Email of owner (if personal transaction) / buddy (if payment).
	 */
	private String email;

	/**
	 * Date of transaction
	 */
	private LocalDate date;

	/**
	 * Description of transaction.
	 */
	private String description;

	/**
	 * Amount of transaction.
	 */
	private BigDecimal amount;

	private BuddyAccount buddyReceiver;
	private BuddyAccount buddyOwner;
	private BigDecimal fee;

	public TransactionDTO(TransactionType type, String email,
			LocalDate date, String description, BigDecimal amount, BigDecimal fee) {

		this.type = type;
		this.email = email;
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.fee = fee;

	}

}
