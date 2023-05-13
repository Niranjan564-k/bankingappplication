package com.banks.demo.service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banks.demo.entity.Account;
import com.banks.demo.entity.AccountStatement;
import com.banks.demo.entity.Transaction;
import com.banks.demo.exception.ResourceNotFoundException;
import com.banks.demo.repository.AccountsRepository;
import com.banks.demo.repository.TransactionRepository;
import com.banks.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountsRepository accountsRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	

	@Override
	public Account saveAccount(Account account) {
		account.setMab();
		
		return accountsRepo.save(account);
	}

	@Override
	public List getAllAccount() {
		return (List) accountsRepo.findAll();
	}

	@Override
	public Account getAccountByaccountId(int accountId) {
		return accountsRepo.findById(accountId).orElseThrow(()->new ResourceNotFoundException("Account Not Found"));
	}

	@Override
	public double MonthlyAvgBal(int accountId) {
		double acct=accountsRepo.findAllByaccountId(accountId).getAcctBal();
		BigDecimal d = new BigDecimal(acct);
		BigDecimal Month=new BigDecimal(12);
		
		BigDecimal Avg = d.divide(Month,2,RoundingMode.CEILING);
		
		double Mav =Avg.doubleValue();
		System.out.print("accccc"+ accountId+":Mav:"+Mav);
		accountsRepo.updatemab(accountId,Mav);
		
		return Mav;
	}

	@Override
	public String validateTransaction(Transaction transaction) {
		String message="valid";
		int fromId=transaction.getFromAccountId();
		int toId=transaction.getToAccountId();
		Account account1=accountsRepo.findByaccountIdEquals(fromId);
		Account account2=accountsRepo.findByaccountIdEquals(toId);
		if(account1 == null) {
			message="Enter a valid From Account Id";
		}else if(account2==null) {
			message="Enter a valid To Account Id";
		}else if(fromId == toId) {
			message="From Account Id and To Account Id must not be equal";
		}else if(account1 != null) {
			double balance=account1.getAcctBal()-transaction.getTransactionAmount();
			if(balance<1) {
				message="No sufficient balance. Your balance is "+account1.getAcctBal();
			}
		}
		
		Transaction save = transactionRepo.save(transaction);
		return message;
	}

	@Override
	public void createTransaction(@Valid Transaction transaction) {
		validateTransaction(transaction);
		 transactionRepo.save(transaction);
		
	}

	@Override
	public void updateAccountBalanceAfterTransaction(@Valid Transaction transaction) {
		int fromId=transaction.getFromAccountId();
		int toId=transaction.getToAccountId();
		Account account1=getAccountByaccountId(fromId);
		Account account2=getAccountByaccountId(toId);
		double amount1=account1.getAcctBal()-transaction.getTransactionAmount();
		double amount2=account2.getAcctBal()+transaction.getTransactionAmount();
		account1.setAcctBal(amount1);
		account2.setAcctBal(amount2);
		accountsRepo.save(account1);
		accountsRepo.save(account2);
		
	}
	
	@Override
	public List<Transaction> getAllTrans() {
		return transactionRepo.findAll();
	}


	@Override
	public boolean validateAccountId(int Id) {
		Account account = accountsRepo.findByaccountIdEquals(Id);
		if(account !=null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Transaction> findTransactionByFromAccountIdEquals(int fromAccountId) {
		return transactionRepo.findByFromAccountIdEquals(fromAccountId);
	}

	@Override
	public List<Transaction> findTransactionByToAccountIdEquals(int toAccountId) {
		return transactionRepo.findByToAccountIdEquals(toAccountId);
	}

	public AccountStatement getStatement(int accountId) {
		Account account = accountsRepo.findByAccountIdEquals(accountId);
		return new AccountStatement(account.getAcctBal(), 
				transactionRepo.findByFromAccountIdEquals(accountId));
	}

	@Override
	public boolean validateAge(Date dob) {
		return false;
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountsRepo.findAll(pageable);
	}
}