package com.openclassrooms.PayMyBuddy.controller;

import javax.servlet.http.HttpServletRequest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.PayMyBuddy.model.BankAccount;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.service.BankAccountService;

import com.openclassrooms.PayMyBuddy.util.LoginEmailRetriever;

@Controller
public class BankAccountController {
	/**
	 * BankAccountController logger.
	 */
	private static Logger LOGGER = LogManager.getLogger(BankAccountController.class);

	/**
	 * IBankAccountService's implement class reference.
	 */
	private BankAccountService bankAccountService;

	/**
	 * LoginEmailRetriever instance.
	 */
	private LoginEmailRetriever loginEmailRetriever;

	/**
	 * Constructor of class BankAccountController. Initialize bankAccountService,
	 * loginEmailRetriever.
	 * 
	 */
	@Autowired
	public BankAccountController(BankAccountService bankAccountService,
			LoginEmailRetriever loginEmailRetriever) {
		this.bankAccountService = bankAccountService;
		this.loginEmailRetriever = loginEmailRetriever;
	}

	/**
	 * Adds a bank account on application.
	 * 
	 */
	
    @GetMapping(value="banktransaction")
    public String getBankTransactions(Model model) {
        return "banktransaction";
    }
    

    
	@PostMapping("/bankAccount")
	public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bankAccount,
			HttpServletRequest request) {
		LOGGER.debug("Bank account POST request with IBAN {} and BIC {}", bankAccount.getIban(),
				bankAccount.getBic());

		String ownerEmail = loginEmailRetriever.getUserName(request);

		BankAccount bankAccount1 = bankAccountService.createBankAccount(ownerEmail, bankAccount);

		LOGGER.info("Bank account POST request - SUCCESS");
		return new ResponseEntity<>(bankAccount1, HttpStatus.CREATED);
	}
}
