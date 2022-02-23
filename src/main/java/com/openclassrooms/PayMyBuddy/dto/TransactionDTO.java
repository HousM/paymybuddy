package com.openclassrooms.PayMyBuddy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.openclassrooms.PayMyBuddy.constant.TransactionType;

@Entity
@DynamicUpdate
@Table(name = "transactionDTO")
public class TransactionDTO {

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TransactionType type;

	public TransactionDTO(TransactionType type, String email,
			LocalDate date, String description, BigDecimal amount, BigDecimal fee) {

		this.type = type;

	}

}
