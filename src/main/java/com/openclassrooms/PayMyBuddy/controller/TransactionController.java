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
//import com.openclassrooms.PayMyBuddy.dto.PaymentTransactionDTO;
//import com.openclassrooms.PayMyBuddy.dto.PersonalTransactionDTO;
//import com.openclassrooms.PayMyBuddy.dto.TransactionDTO;
//import com.openclassrooms.PayMyBuddy.model.Transaction;
//import com.openclassrooms.PayMyBuddy.service.ITransactionService;
//import com.openclassrooms.PayMyBuddy.util.LoginEmailRetriever;
//
//@Controller
//public class TransactionController {
//	/**
//	 * TransactionController logger.
//	 */
//	private static Logger LOGGER = LogManager.getLogger(TransactionController.class);
//
//	/**
//	 * ITransactionService's implement class reference.
//	 */
//	private ITransactionService transactionService;
//
//	/**
//	 * LoginEmailRetriever instance.
//	 */
//	private LoginEmailRetriever loginEmailRetriever;
//
//	/**
//	 * Constructor of class TransactionController. Initialize transactionService,
//	 * loginEmailRetriever.
//	 */
//	@Autowired
//	public TransactionController(ITransactionService transactionService,
//			LoginEmailRetriever loginEmailRetriever) {
//		this.transactionService = transactionService;
//		this.loginEmailRetriever = loginEmailRetriever;
//	}
//
//	/**
//	 * Transfers money from app to bank account.
//	 * 
//	 */
//	@PostMapping("/transfer")
//	public ResponseEntity<Transaction> transferToBankAccount(@Valid @RequestBody PersonalTransactionDTO transfer,
//			HttpServletRequest request) throws Exception {
//		LOGGER.debug("Transfer request with amount {}", transfer.getAmount());
//
//		String ownerEmail = loginEmailRetriever.getUsername(request);
//
//		if (transfer.getAmount() == null || transfer.getAmount().toString().trim().length() == 0) {
//			throw new Exception("Amount is required");
//		}
//
//		Transaction transferSaved = transactionService.transferToBankAccount(ownerEmail, transfer);
//
//		LOGGER.info("Money transfer request - SUCCESS");
//		return new ResponseEntity<>(transferSaved, HttpStatus.OK);
//	}
//
//	/**
//	 * Recharges application's balance from bank account.
//	 *
//	 * 
//	 */
//	@PostMapping("/recharge")
//	public ResponseEntity<Transaction> rechargeBalance(@Valid @RequestBody PersonalTransactionDTO recharge,
//			HttpServletRequest request) throws Exception {
//		LOGGER.debug("Recharge request with amount {}", recharge.getAmount());
//
//		String ownerEmail = loginEmailRetriever.getUsername(request);
//
//		if (recharge.getAmount() == null || recharge.getAmount().toString().trim().length() == 0) {
//			throw new Exception("Amount is required");
//		}
//
//		Transaction rechargeSaved = transactionService.rechargeBalance(ownerEmail, recharge);
//
//		LOGGER.info("Balance recharge request - SUCCESS");
//		return new ResponseEntity<>(rechargeSaved, HttpStatus.OK);
//	}
//
//	@PostMapping("/payment")
//	public ResponseEntity<TransactionDTO> payment(@Valid @RequestBody PaymentTransactionDTO payment,
//			HttpServletRequest request) throws Exception {
//		LOGGER.debug("Payment request of amount {} to buddy {}", payment.getAmount(), payment.getBuddyEmail());
//
//		String ownerEmail = loginEmailRetriever.getUsername(request);
//
//		if (payment.getAmount() == null || payment.getAmount().toString().trim().length() == 0) {
//			throw new Exception("Amount is required");
//		}
//
//		TransactionDTO paymentSaved = transactionService.payMyBuddy(ownerEmail, payment);
//
//		LOGGER.info("Payment request - SUCCESS");
//		return new ResponseEntity<>(paymentSaved, HttpStatus.OK);
//	}
//}
