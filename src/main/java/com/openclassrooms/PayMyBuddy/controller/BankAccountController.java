//package com.openclassrooms.PayMyBuddy.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.openclassrooms.PayMyBuddy.dto.BankAccountDTO;
//import com.openclassrooms.PayMyBuddy.service.IBankAccountService;
//import com.openclassrooms.PayMyBuddy.util.LoginEmailRetriever;
//
//@Controller
//public class BankAccountController {
//	/**
//	 * BankAccountController logger.
//	 */
//	private static Logger LOGGER = LogManager.getLogger(BankAccountController.class);
//
//	/**
//	 * IBankAccountService's implement class reference.
//	 */
//	private IBankAccountService bankAccountService;
//
//	/**
//	 * LoginEmailRetriever instance.
//	 */
//	private LoginEmailRetriever loginEmailRetriever;
//
//	/**
//	 * Constructor of class BankAccountController. Initialize bankAccountService,
//	 * loginEmailRetriever.
//	 * 
//	 */
//	@Autowired
//	public BankAccountController(IBankAccountService bankAccountService,
//			LoginEmailRetriever loginEmailRetriever) {
//		this.bankAccountService = bankAccountService;
//		this.loginEmailRetriever = loginEmailRetriever;
//	}
//
//	/**
//	 * Adds a bank account on application.
//	 * 
//	 */
//	@PostMapping("/bankAccount")
//	public ResponseEntity<BankAccountDTO> addBankAccount(@Valid @RequestBody BankAccountDTO bankAccount,
//			HttpServletRequest request) {
//		LOGGER.debug("Bank account POST request with IBAN {} and BIC {}", bankAccount.getIban(),
//				bankAccount.getBic());
//
//		String ownerEmail = loginEmailRetriever.getUsername(request);
//
//		BankAccountDTO bankAccount1 = bankAccountService.createBankAccount(ownerEmail, bankAccount);
//
//		LOGGER.info("Bank account POST request - SUCCESS");
//		return new ResponseEntity<>(bankAccount1, HttpStatus.CREATED);
//	}
//}
