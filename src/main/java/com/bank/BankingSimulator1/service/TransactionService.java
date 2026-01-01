package com.bank.BankingSimulator1.service;
import java.math.BigDecimal;

import com.bank.BankingSimulator1.exception.AccountNotFoundException;
import com.bank.BankingSimulator1.exception.InsufficientBalanceException;
import com.bank.BankingSimulator1.exception.InvalidAccountException;
import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.repository.TransactionRepository;
import com.bank.BankingSimulator1.util.FileReportUtil;

public class TransactionService {
    private   AccountService accountService;
    private TransactionRepository transactionRepository;
    private AlertService alertService;
	public TransactionService(AccountService accountService, TransactionRepository transactionRepository,AlertService alertService) {
		this.accountService = accountService;
		this.transactionRepository = transactionRepository;
		this.alertService=alertService;
	}
	
	public void Deposit(String account_number,BigDecimal amount) throws InvalidAccountException, AccountNotFoundException{
		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			throw new InvalidAccountException("Amount should not be negative");
		}
		Account acc=accountService.getAccount(account_number);
		acc.credit(amount);
		FileReportUtil.writeLine("DEPOSIT | Acc: "+account_number+" | Amount: "+amount);
		transactionRepository.logTransaction("Deposit", account_number, amount.doubleValue(), null);
		alertService.checkBalance(acc);
		//alertService.depositAlert(acc, amount);
	}
	public void Transfer(String fromAcc,String toAcc,BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException  {
		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("Amount should not be negative");
		}
		Account sender=accountService.getAccount(fromAcc);
		Account reciever=accountService.getAccount(toAcc);
		if(sender.getBalance().compareTo(amount)<0) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		sender.debit(amount);
		reciever.credit(amount);
		FileReportUtil.writeLine("TRANSFER | FromAccount: "+fromAcc+" | ToAccount: "+toAcc+" | Amount :"+amount);
		transactionRepository.logTransaction("Transfer", fromAcc, amount.doubleValue(), toAcc);
		alertService.checkBalance(sender);
		alertService.checkBalance(reciever);
	}
	public void withDraw(String account_Number,BigDecimal amount) throws InvalidAccountException, AccountNotFoundException,InsufficientBalanceException{
		if(amount.compareTo(BigDecimal.ZERO)<=0) {
			throw new InvalidAccountException("Amount should not be negative");
		}
		Account account=accountService.getAccount(account_Number);
		if(account.getBalance().compareTo(amount)<=0) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		account.debit(amount);
		FileReportUtil.writeLine("WITHDRAW | Acc: "+account_Number+" | Amount: "+amount);
		transactionRepository.logTransaction("WithDraw", account_Number, amount.doubleValue(), null);
		alertService.checkBalance(account);
	}
    
}
