//package com.openclassrooms.PayMyBuddy.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.openclassrooms.PayMyBuddy.model.BankAccount;
//
//public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
//	@Query(value = "SELECT * "
//			+ "FROM transactions_bank t "
//			+ "WHERE t.user_id = :userid", nativeQuery = true)
//	public Page<BankAccount> findBankTransactionByUserId(@Param("userid") Long userid, Pageable pageRequest);
//}
