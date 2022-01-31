package com.openclassrooms.PayMyBuddy.service;

import com.openclassrooms.PayMyBuddy.model.BankAccount;

public interface IBankAccountService {

	BankAccount createBankAccount(String ownerEmail, BankAccount bankAccount);

}
