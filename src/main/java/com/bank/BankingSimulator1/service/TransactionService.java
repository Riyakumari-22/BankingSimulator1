package com.bank.BankingSimulator1.service;

import java.math.BigDecimal;

import com.bank.BankingSimulator1.repository.TransactionRepository;
import com.bank.BankingSimulator1.exception.AccountNotFoundException;
import com.bank.BankingSimulator1.exception.InsufficientBalanceException;
import com.bank.BankingSimulator1.exception.InvalidAccountException;
import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.util.FileReportUtil;

public class TransactionService {
	private AccountService accountService;
	private TransactionRepository transactionRepository;
	private AlertService alertService;
	
	
	public TransactionService(AccountService accountService ,TransactionRepository transactionRepository,AlertService alertService) {
		this.accountService = accountService;
		this.transactionRepository = transactionRepository;
		this.alertService = alertService;
	}
	
	public void deposite(String accNo ,BigDecimal amount) throws InvalidAccountException, AccountNotFoundException {
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidAccountException("Amount should not be negative");
		}
		
		Account acc = accountService.getAccount(accNo);
		acc.credit(amount);
		
		FileReportUtil.writeLine("DEPOSITE | Acc: "+accNo+" | Amount: "+amount);
		
		transactionRepository.logTransaction("DEPOSITE", accNo, amount.doubleValue(), null);
		
		alertService.checkBalance(acc);
	 
	}
	
	
	public void withdraw(String accNo, BigDecimal amount) throws InvalidAccountException, AccountNotFoundException, InsufficientBalanceException {
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidAccountException("Amount should not be negative");
		}
		
		Account account = accountService.getAccount(accNo);
		
		if(account.getBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		
		account.debit(amount);
		
		FileReportUtil.writeLine("WITHDRAW | Acc: "+accNo+" | Amount: "+amount);
		
		transactionRepository.logTransaction("WITHDRAW", accNo, amount.doubleValue(), null);
		
		alertService.checkBalance(account);
	}
	
	public void tranfer(String fromAcc,String toAcc,BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException {
		
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Amount should not be negative");
		}
		
		Account sender = accountService.getAccount(fromAcc);
		Account recevier = accountService.getAccount(toAcc);
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		
		sender.debit(amount);
		recevier.credit(amount);
		
		FileReportUtil.writeLine("TRANSFER | FromAcc: "+fromAcc+" | ToAccount: "+toAcc+" | Amount "+amount);
		
		transactionRepository.logTransaction("TRANSFER", fromAcc, amount.doubleValue(), toAcc);
		
		alertService.checkBalance(sender);
		alertService.checkBalance(recevier);
	}
 
	 
}