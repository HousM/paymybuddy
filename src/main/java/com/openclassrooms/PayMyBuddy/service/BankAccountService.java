package com.openclassrooms.PayMyBuddy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.model.BankAccount;
import com.openclassrooms.PayMyBuddy.model.User;
import com.openclassrooms.PayMyBuddy.repository.BankAccountRepository;

@Service
public class BankAccountService {

	/**
	 * BankAccountService logger.
	 */
	private static Logger LOGGER = LogManager.getLogger(BankAccountService.class);

	/**
	 * BankAccountRepository instance.
	 */
	private BankAccountRepository bankAccountRepository;

	/**
	 * IUserService's implement class reference.
	 */
	private UserService userService;

	/**
	 * Constructor of class BankAccountService. Initialize bankAccountRepository,
	 * userService.
	 * 
	 */
	@Autowired
	public BankAccountService(BankAccountRepository bankAccountRepository, UserService userService) {
		this.bankAccountRepository = bankAccountRepository;
		this.userService = userService;
	}


	public BankAccount createBankAccount(String ownerEmail, BankAccount bankAccount) {
		LOGGER.debug("Inside BankAccountService.createBankAccount for username : " + ownerEmail);
		User user = userService.getUserByEmail(ownerEmail);

		if (user.getBankAccount() != null) {
			throw new UsernameNotFoundException("Your bank Account is already registered");
		}
		BankAccount bankAccount1 = bankAccountRepository.save(user, bankAccount.getIban(), bankAccount.getBic());
		return bankAccount1;

	}
	


}
