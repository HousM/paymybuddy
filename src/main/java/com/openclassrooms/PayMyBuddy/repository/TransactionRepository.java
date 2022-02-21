//package com.openclassrooms.PayMyBuddy.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.openclassrooms.PayMyBuddy.model.Transaction;
//
//public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//
//	@Query(value = "SELECT * "
//			+ "FROM transactions_user "
//			+ "WHERE usersource_id = :usersourceid OR userdestination_id = :usersourceid ", nativeQuery = true)
//	public Page<Transaction> findUserTransactionByUserId(@Param("usersourceid") Long usersourceid,
//			Pageable pageRequest);
//}
