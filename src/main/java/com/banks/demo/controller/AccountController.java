package com.banks.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banks.demo.entity.Account;
import com.banks.demo.entity.AccountStatement;
import com.banks.demo.entity.ApiResponse;
import com.banks.demo.service.AccountService;
import com.banks.demo.service.EmailService;



@RestController
@RequestMapping("api/v1")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EmailService emailService;

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@PostMapping("/account")
	public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account){
		Account saveAccount = this.accountService.saveAccount(account);
		return new ResponseEntity<Account>(saveAccount,HttpStatus.OK);
	}
	
	
	@GetMapping("/AllAccount")
	public ResponseEntity<List<Account>> getAllAccount()

	{
		logger.info("get all method of Account controller");
		List<Account> allAccount = this.accountService.getAllAccount();
		return new ResponseEntity<List<Account>> (allAccount, HttpStatus.OK);
	}

	
	@GetMapping("accountById/{accountId}")
	public ResponseEntity<Account> getAccountByaccountId(@PathVariable("accountId") int accountId) {
		logger.info("Get Customer details By Account Id ");
		Account account = this.accountService.getAccountByaccountId(accountId);
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}
	
	@GetMapping("/AcctBal/{accountId}")
	public ApiResponse getBalance(@Valid @PathVariable int accountId) {
		logger.info("get AccountBalance by id");
		Account accountByaccountId = accountService.getAccountByaccountId(accountId);
		ApiResponse apiResponse = new ApiResponse(accountByaccountId.getCustomerName(),
				accountByaccountId.getAcctBal());
		return apiResponse;
	}
	
	@GetMapping("MonthlyAvgBal/{accountId}")
	public double getMonthlyAverageBalanceById(@PathVariable int accountId, Account account) {
		logger.trace("get monthlyAvgBal by Id");

		double monthlyAvgBal = accountService.MonthlyAvgBal(accountId);

		return monthlyAvgBal;
	}
	
	@GetMapping("/accountPageable")
	public Page<Account> accountPageable(Pageable pageable) {
		logger.debug("Pageable OF 5 Accounts Are Given");
		return accountService.findAll(pageable);
	}

//	@GetMapping("/Transaction")
//	public ResponseEntity<Transaction>getAlltransaction(
//			@RequestParam(value = "pageNumber", required = false) int pageNumber, 
//			@RequestParam(value = "pageSize", required = false) int pageSize,
//			@RequestParam(value = "sortBy", required = false) String sortby,
//			@RequestParam(value = "sortDirection", required = false) String sortDirection){
//		Transaction Alltransaction = this.accountService.getAlltransaction(pageNumber, pageSize, sortby, sortDirection);
//		return new ResponseEntity<Transaction>(Alltransaction, HttpStatus.OK);
//	}
	
	@GetMapping("/Statement/{accountId}")
	public AccountStatement getStatement(@PathVariable("accountId") int accountId) {
		logger.info("Account Statement Has been Generated");
		 return accountService.getStatement(accountId);
	}
	
	@GetMapping("/sendEmail")
	public String sendPromotionalEmails(){
		logger.info("Sending Promotional email");
		String message=emailService.sendEmailAfterCheckingMab();
		logger.info("Promotional email sent Successfully");
		return message;
	}
	
	
	
}
