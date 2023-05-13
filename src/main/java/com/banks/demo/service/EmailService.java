package com.banks.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.banks.demo.entity.Account;
import com.banks.demo.repository.AccountsRepository;

@Service
public class EmailService {
	
	@Autowired
	private AccountsRepository accountsRepo;

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;

	public String sendEmailAfterCheckingMab() {
		List<Account> accounts = accountsRepo.checkMab();
		
		if (!accounts.isEmpty()) {	

			List<String> emailList = accounts.stream().map(Account::getEmailId).collect(Collectors.toList());

			String email = emailList.get(0);
			
			try {
				SimpleMailMessage mailMessage = new SimpleMailMessage();

				
				mailMessage.setFrom("bniranjan2014@gmail.com");
				mailMessage.setTo(email);
				mailMessage.setSubject("Promotional Email From BNK Bank");
				mailMessage.setText(
						"Dear Sir/Mam,\nGreetings from BNK bank,\n\nWe want to bring to your notice that we have an ongoing NFO which is "
								+ "BNK flexicap fund managed by Mr BNK.\nIf interested write back to us at ....@gmail.com.\n\nRegards,\nBNK Bank.");

				
				
	            javaMailSender.send(mailMessage);
	            System.out.println("Mail send ...");

				return "Promotional email sent successfully";
			}
			catch (Exception e) {
				System.out.println(e);
				return "Error while Sending Mail";
				}
			}else {
				return "No Active accounts maintain $1000 MAB to sent an Promotional Email";
			}
	}
}


	


