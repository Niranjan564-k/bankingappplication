package com.banks.demo.entity;

import java.util.List;

public class AccountStatement {
	
	private double currentBalance;

	private List<Transaction> transactionHistory;

	public AccountStatement() {

	}

	public AccountStatement(double currentBalance, List<Transaction> transactionHistory) {
		super();
		this.currentBalance = currentBalance;
		this.transactionHistory = transactionHistory;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public List<Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<Transaction> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	
	

}
