package com.banks.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banks.demo.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByFromAccountIdEquals(int fromAccountId);

	List<Transaction> findByToAccountIdEquals(int toAccountId);

	

	

}
