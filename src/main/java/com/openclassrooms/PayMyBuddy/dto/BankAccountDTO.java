package com.openclassrooms.PayMyBuddy.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.openclassrooms.PayMyBuddy.model.User;
import com.sun.istack.NotNull;

public class BankAccountDTO {

	@NotNull
	@NotEmpty(message = "IBAN code is required")
	@Length(min = 14, max = 34, message = "Please enter a valid IBAN code")
	private String iban;

	private User user;
	/**
	 * BIC code of bank account.
	 */
	@NotNull
	@NotEmpty(message = "BIC code is required")
	@Length(min = 8, max = 11, message = "Please enter a valid BIC code")
	private String bic;

	public BankAccountDTO(String iban, String bic) {
		super();
		this.iban = iban;
		this.bic = bic;

	}

	public String getBic() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIban() {
		// TODO Auto-generated method stub
		return null;
	}

}
