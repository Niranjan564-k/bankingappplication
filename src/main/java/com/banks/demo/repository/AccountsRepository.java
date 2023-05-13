package com.banks.demo.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banks.demo.entity.Account;



public interface AccountsRepository extends JpaRepository<Account, Integer> {

	
	@Query("select u from Account u where u.accountId = :accountId")
	public Account getAllDetailsByaccountId(@Param("accountId") int accountId);

	@Transactional
	@Modifying
	@Query("update Account u set u.mab = :Mav where u.accountId = :accountId ")
	public void updatemab(@Param("accountId")int accountId, @Param("Mav")double mAV);
	
	@Query("from Account ")
	public Account findMonthlyAverageBalancebyaccountId(int accountId);
	
	public Account findAllByaccountId(int accountId);

	public Account findByaccountIdEquals(int fromId);

	public Account findByAccountIdEquals(int accountId);
	
	@Query("from Account where mab>=1000 AND acctBal>0 AND acctStatus='Active'")
	public List<Account> checkMab();

	

	

	




}
