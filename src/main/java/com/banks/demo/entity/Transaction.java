package com.banks.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transactionId;
	
	private int fromAccountId;
	
	private int toAccountId;
	
	private double transactionAmount;
	
	private LocalDateTime transactionAt;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Transaction(int fromAccountId, int toAccountId, double transactionAmount) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.transactionAmount = transactionAmount;
	}



	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public int getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public int getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public LocalDateTime getTransactionAt() {
		return transactionAt;
	}

	public void setTransactionAt(LocalDateTime transactionAt) {
		this.transactionAt = transactionAt;
	}


	
	
	

}
