package com.openclassrooms.PayMyBuddy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.PayMyBuddy.model.BuddyAccount;
import com.openclassrooms.PayMyBuddy.repository.BuddyAccountRepository;

@Service
public class BuddyAccountService {

	/**
	 * BuddyAccountService logger.
	 */
	private static Logger LOGGER = LogManager.getLogger(BuddyAccountService.class);

	/**
	 * BuddyAccountRepository instance.
	 */
	private BuddyAccountRepository buddyAccountRepository;

	/**
	 * Constructor of class BuddyAccountService. Initialize buddyAccountRepository.
	 * 
	 */
	public BuddyAccountService(BuddyAccountRepository buddyAccountRepository) {
		this.buddyAccountRepository = buddyAccountRepository;
	}
	public BuddyAccountService() {}

	/**
	 * Saves BuddyAccount by calling buddyAccountRepository's save method.
	 * 
	 */
	public BuddyAccount saveBuddyAccount(Long id) {
		LOGGER.debug("Inside BuddyAccountService.saveBuddyAccount");
		return buddyAccountRepository.findBuddyAccountById(id);
	}
}
