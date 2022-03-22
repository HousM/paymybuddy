package com.openclassrooms.PayMyBuddy.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.constant.TransactionType;

import com.openclassrooms.PayMyBuddy.model.BuddyAccount;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import com.openclassrooms.PayMyBuddy.repository.UserRepository;
import com.openclassrooms.PayMyBuddy.util.FeeCalculator;

@Service
public class TransactionService {

	/**
	 * TransactionRepository instance.
	 */

	private TransactionRepository transactionRepository;

	private FeeCalculator feeCalculator;

	private UserRepository userRepository;

	public Transaction saveTransaction(User user, TransactionType type, BuddyAccount buddyAccountreciver,
			BuddyAccount buddyAccountowner, float amount) {
		Transaction transaction = new Transaction();
		String email = user.getEmail();
		float fee = feeCalculator.getFee(amount);
		User owner = userRepository.findByEmail(email);
		User reciver = userRepository.getUserById(user.getId());

		float balanceowner = buddyAccountowner.getbalance();
		float balancereciver = buddyAccountreciver.getbalance();

		if (balanceowner >= amount) {
			transaction.setBuddyReciver(buddyAccountreciver);
			reciver = buddyAccountreciver.setuser(reciver);
			transaction.setBuddyOwner(buddyAccountowner);
			owner = buddyAccountowner.setuser(owner);
			transaction.setType(type);
			transaction.setDate(LocalDateTime.now());
			transaction.setAmount(amount);
			transaction.setFee(fee);

			transaction.getBuddyOwner().setbalance(balanceowner - amount);
			transaction.getBuddyReciver().setbalance(balancereciver + (amount - fee));
			return transactionRepository.save(transaction);
		} else {
			return null;
		}
	}

	public List<Transaction> findByUser(User user) {
		return transactionRepository.findUserTransactionByUserId(user);
	}

}
