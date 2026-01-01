package com.bank.BankingSimulator1.model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public class Account {

	private static final AtomicLong COUNTER=new AtomicLong(1000000L);
	private final String accountNumber;
	private String holderName;
	private String email;
	private BigDecimal balance;
	public Account(String holderName, String email, BigDecimal openingbalance) {
		this.accountNumber=String.valueOf(COUNTER.getAndIncrement());
		this.holderName = holderName;
		this.email = email;
		this.balance = openingbalance;
	}
	public synchronized void  credit(BigDecimal amount) {
		this.balance=this.balance.add(amount);
	}
	public synchronized void debit(BigDecimal amount) {
		this.balance=this.balance.subtract(amount);
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public String getEmail() {
		return email;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", holderName=" + holderName + ", email=" + email
				+ ", balance=" + balance + "]";
	}
	

}
