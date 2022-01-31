package com.openclassrooms.PayMyBuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.PayMyBuddy.model.BuddyAccount;

public interface BuddyAccountRepository extends JpaRepository<BuddyAccount, Long> {
	BuddyAccount findByRolename(String rolename);
}
