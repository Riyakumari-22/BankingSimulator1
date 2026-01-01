package com.bank.BankingSimulator1.service;

import java.math.BigDecimal;

import com.bank.BankingSimulator1.exception.AccountNotFoundException;
import com.bank.BankingSimulator1.exception.InvalidAccountException;
import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.repository.AccountRepository;
import java.util.*;

public class AccountService {
 private AccountRepository repo;

 public AccountService(AccountRepository repo) {
	this.repo = repo;																																																										
 }
 public Account createAccount(String holderName,String email,BigDecimal openingBalance) throws InvalidAccountException{
	 if(openingBalance.compareTo(BigDecimal.ZERO)<0) {
		 throw new InvalidAccountException("Opening balance cannot be negative");
	 }
	 Account account=new Account(holderName,email,openingBalance);
	 repo.save(account);
	 return account;
 }
 public Account getAccount(String accountNumber) throws AccountNotFoundException{
	Account account= repo.findAccountByNumber(accountNumber);
	if(account == null) {
		throw new AccountNotFoundException("Account Not Found"+accountNumber);
	}
	return account;
 }
 public Collection<Account> listAll(){
	return repo.findAll();
 }
}
