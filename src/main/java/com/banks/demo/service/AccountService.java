package com.banks.demo.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banks.demo.entity.Account;
import com.banks.demo.entity.AccountStatement;
import com.banks.demo.entity.Transaction;



@Service
public interface AccountService {
	
	public Account saveAccount(@Valid Account account);
	public List<Account> getAllAccount();
	public Account getAccountByaccountId(int accountId);
	public double MonthlyAvgBal(int accountId);
	public String validateTransaction(Transaction transaction);
	public void createTransaction(@Valid Transaction transaction);
		
	public void updateAccountBalanceAfterTransaction(@Valid Transaction transaction);
	public List<Transaction> getAllTrans();
	public boolean validateAccountId(int Id);
	public List<Transaction> findTransactionByFromAccountIdEquals(int fromAccountId);
	public List<Transaction> findTransactionByToAccountIdEquals(int toAccountId);
	public AccountStatement getStatement(int accountId);
	public boolean validateAge(Date dob);
	public Page<Account> findAll(Pageable pageable);
	
	
	
	
	
	

	
    



	

	

	
		
		
	

}