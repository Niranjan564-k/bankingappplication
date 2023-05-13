package com.banks.demo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	@NotNull
	private String customerName;
	
	@Column(unique = true)
	private String pan;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="MM/dd/yyyy")
	private Date dob;
	
	@NotEmpty
	private String acctType;
	
	@NotEmpty
	private String acctStatus;
	
	@Min(value = 1, message = "Balance must be >=1")
	private double acctBal;
	
	@NotEmpty
	@Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "enter valid phonenumber")
	private String phoneNumber;
	
	@NotEmpty
	@Email(message = " Enter a Valid Email Id ")
	private String emailId;
	
	private double mab;
	

	public Account(int accountId, @NotNull String customerName, String pan, Date dob, @NotEmpty String acctType,
			@NotEmpty String acctStatus, @Min(value = 1, message = "Balance must be >=1") double acctBal,
			@NotEmpty @Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "enter valid phonenumber") String phoneNumber,
			@NotEmpty @Email(message = " Enter a Valid Email Id ") String emailId, double mab) {
		super();
		this.accountId = accountId;
		this.customerName = customerName;
		this.pan = pan;
		this.dob = dob;
		this.acctType = acctType;
		this.acctStatus = acctStatus;
		this.acctBal = acctBal;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.mab = mab;
	}

	public Account() {
		super();
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getAcctStatus() {
		return acctStatus;
	}

	public void setAcctStatus(String acctStatus) {
		this.acctStatus = acctStatus;
	}

	public double getAcctBal() {
		return acctBal;
	}

	public void setAcctBal(double acctBal) {
		this.acctBal = acctBal;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public double getMab() {
		return mab;
	}

	public void setMab() {
		BigDecimal b= new BigDecimal(getAcctBal());
		BigDecimal Month=new BigDecimal(12);
		BigDecimal Avg = b.divide(Month,2,RoundingMode.CEILING);
		double Mav =Avg.doubleValue();
		System.out.println("000000:"+Mav);
		this.mab =Mav;
	}

	

	


	
	
	
}
