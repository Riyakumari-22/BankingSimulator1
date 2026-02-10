package com.bank.BankingSimulator1;

import java.math.BigDecimal;

import com.bank.BankingSimulator1.repository.AccountRepository;
import com.bank.BankingSimulator1.repository.TransactionRepository;
import com.bank.BankingSimulator1.exception.AccountNotFoundException;
import com.bank.BankingSimulator1.exception.InsufficientBalanceException;
import com.bank.BankingSimulator1.exception.InvalidAccountException;
import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.service.AccountService;
import com.bank.BankingSimulator1.service.AlertService;
import com.bank.BankingSimulator1.service.TransactionService;

public class AlertTest {
	public static void main(String[] args) {
		
		AccountRepository accountRepository = new AccountRepository();
		AccountService accountService = new AccountService(accountRepository);
		
		TransactionRepository trxRepo = new TransactionRepository();
		AlertService alertService = new AlertService(new BigDecimal("1000"));
		
		TransactionService trxService = new TransactionService(accountService,trxRepo,alertService);
		
		/*try {
			Account account = accountService.createAccount("chinni", "chinnikrishnamekala1@gmail.com", new BigDecimal("500"));
			trxService.deposite(account.getAccountNumber(),new BigDecimal("2000"));
			System.out.println("Amount is deposited Successfully..");
			System.out.println("Total Balance : "+account.getBalance());
		} catch (InvalidAmountException | AccountNotFoundException e) {
			 
			e.printStackTrace();
		}*/
		
		try {
			Account account = accountService.createAccount("Chinnikrishna", "chinnikrishnamekala1@gmail.com", new BigDecimal("5000"));
			trxService.withdraw(account.getAccountNumber(),new BigDecimal("4500"));
			System.out.println("Amount is withdrawn Successfully..");
			System.out.println("Total Balance : "+account.getBalance());
			
		} catch (InvalidAccountException | AccountNotFoundException | InsufficientBalanceException e) {
			 
			e.printStackTrace();
		}
		
		
		
		
	}
}