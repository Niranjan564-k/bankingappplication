package com.banks.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banks.demo.entity.Transaction;
import com.banks.demo.service.AccountService;


@RestController
@RequestMapping("api/v1")
public class TransactionController {
	
	@Autowired
	private AccountService accountService;
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@PostMapping("/Transaction/add")
	public ResponseEntity<Object> createTransaction(@Valid @RequestBody Transaction transaction){
		logger.info("Transaction Successful");
		String message=accountService.validateTransaction(transaction);
		if(message=="valid") {
			transaction.setTransactionAt(LocalDateTime.now());
			accountService.createTransaction(transaction);
			accountService.updateAccountBalanceAfterTransaction(transaction);
			return ResponseEntity.status(HttpStatus.OK).body("Transaction Successful");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(message);
	}
	
	}
	

	@GetMapping("/Transaction/All/")
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		logger.debug("Get All Transaction");
		List<Transaction> transactions=accountService.getAllTrans();
		return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
	
	@GetMapping("/AllTransaction/from/{fromId}")
	public ResponseEntity<Object>getAlltransactionByFromId(@PathVariable("fromId") int fromId){
		
		boolean valid=accountService.validateAccountId(fromId);
		if(valid == true) {			
			List<Transaction> transactions=accountService.findTransactionByFromAccountIdEquals(fromId);
			return ResponseEntity.status(HttpStatus.OK).body(transactions);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter a Valid From Account Id");
		}
	}
	
	@GetMapping("/Transaction/AllTransaction/to/{toId}")
	public ResponseEntity<Object>getAllTransactionByToId(@PathVariable("toId") int toId){
		
		boolean valid=accountService.validateAccountId(toId);
		if(valid == true) {			
			List<Transaction> transactions=accountService.findTransactionByToAccountIdEquals(toId);
			return ResponseEntity.status(HttpStatus.OK).body(transactions);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter a Valid To Account Id");
		}	
	}
}
