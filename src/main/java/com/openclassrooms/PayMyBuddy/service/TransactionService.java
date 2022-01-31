package com.openclassrooms.PayMyBuddy.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.constant.TransactionType;
import com.openclassrooms.PayMyBuddy.dto.PaymentTransactionDTO;
import com.openclassrooms.PayMyBuddy.dto.PersonalTransactionDTO;
import com.openclassrooms.PayMyBuddy.dto.TransactionDTO;
import com.openclassrooms.PayMyBuddy.model.BuddyAccount;
import com.openclassrooms.PayMyBuddy.model.Transaction;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.TransactionRepository;
import com.openclassrooms.PayMyBuddy.util.FeeCalculator;

@Service
public class TransactionService implements ITransactionService {
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TransactionType type;
	/**
	 * TransactionService logger.
	 */
	private static Logger LOGGER = LogManager.getLogger(TransactionService.class);

	/**
	 * TransactionRepository instance.
	 */
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * IUserService's implement class reference.
	 */
	private IUserService userService;

	/**
	 * IBuddyAccountService's implement class reference.
	 */
	private IBuddyAccountService buddyAccountService;

	/**
	 * FeeCalculator instance.
	 */
	private FeeCalculator feeCalculator;

	/**
	 * Calls UserService's getUserByEmail method to retrieves the user with the
	 * given email
	 */
	@Override
	@Transactional
	public Transaction transferToBankAccount(String ownerEmail, PersonalTransactionDTO transfer) {
		LOGGER.debug("Inside TransactionService.transferToBankAccount for {}", ownerEmail);
		User user = userService.getUserByEmail(ownerEmail);

		if (user.getBankAccount() == null) {
			throw new UsernameNotFoundException("You need to register your Bank Account to transfer money");
		}
		BuddyAccount buddyAccount = user.getBuddyAccount();
		BigDecimal balance = buddyAccount.getBalance();
		BigDecimal amount = transfer.getAmount();

		// Calculates the transaction fee.
		BigDecimal fee = feeCalculator.getFee(amount).setScale(2, RoundingMode.HALF_UP);

		// Checks if there is sufficient funds on balance for the transfer.
		if (balance.compareTo(amount.add(fee)) < 0) {
			throw new UsernameNotFoundException("Insufficient funds on your balance for this transfer!");
		}
		// Updates balance by subtracting amount and fee.
		buddyAccount.setBalance(balance.subtract(amount.add(fee)));
		BuddyAccount buddyAccountSaved = buddyAccountService.saveBuddyAccount(buddyAccount);

		Transaction transaction = transactionRepository.save(new Transaction(type.TRANSFER,
				buddyAccountSaved, user.getBankAccount(), LocalDate.now(), transfer.getDescription(), amount, fee));

		Transaction transaction1 = new Transaction(type, transaction.getBuddyOwner().getOwner()
				.getEmail(), transaction.getDate(), transaction.getDescription(), transaction.getAmount(),
				transaction.getFee());

		return transaction1;
	}

	/**
	 * Calls UserService's getUserByEmail method to retrieves the user with the
	 * given email
	 */

	@Override
	public Transaction rechargeBalance(String ownerEmail, PersonalTransactionDTO recharge) {
		LOGGER.debug("Inside TransactionService.rechargeBalance for {}", ownerEmail);
		User user = userService.getUserByEmail(ownerEmail);

		if (user.getBankAccount() == null) {
			throw new UsernameNotFoundException("You need to register your Bank Account to recharge balance");
		}
		BuddyAccount buddyAccount = user.getBuddyAccount();
		BigDecimal balance = buddyAccount.getBalance();
		BigDecimal amount = recharge.getAmount();

		// Calculates the transaction fee.
		BigDecimal fee = feeCalculator.getFee(amount).setScale(2, RoundingMode.HALF_UP);

		// Updates balance by subtracting amount and fee.
		buddyAccount.setBalance(balance.subtract(fee).add(amount));
		buddyAccountService.saveBuddyAccount(buddyAccount);

		Transaction transaction = transactionRepository.save(new Transaction(TransactionType.RECHARGE, buddyAccount,
				user.getBankAccount(), LocalDate.now(), recharge.getDescription(), amount, fee));

		Transaction transaction1 = new Transaction(transaction.getType(), transaction.getBuddyOwner().getOwner()
				.getEmail(), transaction.getDate(), transaction.getDescription(), transaction.getAmount(),
				transaction.getFee());

		return transaction1;
	}

	/**
	 * Calls UserService's getUserByEmail method to retrieves the sender and
	 * receiver
	 */

	@Override
	@Transactional
	public TransactionDTO payMyBuddy(String ownerEmail, PaymentTransactionDTO payment) {
		LOGGER.debug("Inside TransactionService.payMyBuddy");
		User sender = userService.getUserByEmail(ownerEmail);
		User receiver = userService.getUserByEmail(payment.getBuddyEmail());

		// Checks if receiver is registered in sender contacts.
		if (!sender.getContacts().contains(receiver)) {
			throw new UsernameNotFoundException(
					"This buddy is not in your contacts. Please add to contacts for payment");
		}
		BuddyAccount senderBuddyAccount = sender.getBuddyAccount();
		BigDecimal senderBalance = senderBuddyAccount.getBalance();
		BigDecimal amount = payment.getAmount();

		// Calculates the transaction fee.
		BigDecimal fee = feeCalculator.getFee(amount).setScale(2, RoundingMode.HALF_UP);

		// Checks if there is sufficient funds on sender balance for the payment.
		if (senderBalance.compareTo(amount.add(fee)) < 0) {
			throw new BadJwtException("Insufficient funds for this transfer. Please recharge your balance!");
		}
		// Updates sender balance by subtracting amount and fee.
		senderBuddyAccount.setBalance(senderBalance.subtract(amount.add(fee)));
		buddyAccountService.saveBuddyAccount(senderBuddyAccount);

		BuddyAccount receiverBuddyAccount = receiver.getBuddyAccount();
		// Updates receiver balance by adding amount.
		receiverBuddyAccount.setBalance(receiverBuddyAccount.getBalance().add(amount));
		buddyAccountService.saveBuddyAccount(receiverBuddyAccount);

		Transaction transaction = transactionRepository.save(new Transaction(TransactionType.PAYMENT,
				senderBuddyAccount, receiverBuddyAccount, LocalDate.now(), payment.getDescription(), amount, fee));

		TransactionDTO transaction1 = new TransactionDTO(transaction.getType(), transaction.getBuddyReceiver()
				.getOwner().getEmail(), transaction.getDate(), transaction.getDescription(), transaction.getAmount(),
				transaction.getFee());

		return transaction1;
	}
}
