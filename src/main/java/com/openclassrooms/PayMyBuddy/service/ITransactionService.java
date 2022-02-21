//package com.openclassrooms.PayMyBuddy.service;
//
//import com.openclassrooms.PayMyBuddy.dto.PaymentTransactionDTO;
//import com.openclassrooms.PayMyBuddy.dto.PersonalTransactionDTO;
//import com.openclassrooms.PayMyBuddy.dto.TransactionDTO;
//import com.openclassrooms.PayMyBuddy.model.Transaction;
//
//public interface ITransactionService {
//
//	/**
//	 * Transfers money from application to bank account.
//	 */
//	Transaction transferToBankAccount(String ownerEmail, PersonalTransactionDTO transfer);
//
//	/**
//	 * Recharges the app balance by transferring money from bank account.
//	 */
//	Transaction rechargeBalance(String ownerEmail, PersonalTransactionDTO recharge);
//
//	/**
//	 * Pays a buddy on application.
//	 */
//	TransactionDTO payMyBuddy(String ownerEmail, PaymentTransactionDTO payment);
//}
