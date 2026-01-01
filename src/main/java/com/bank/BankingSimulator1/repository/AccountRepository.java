package com.bank.BankingSimulator1.repository;
import java.util.*;

import com.bank.BankingSimulator1.model.Account;
public class AccountRepository {
private final Map<String,Account> accounts=new HashMap<>();
public Account findAccountByNumber(String accountNumber) {
	Account account=accounts.get(accountNumber);
	return account;
}
	public Collection<Account> findAll(){
	return accounts.values();	
	}
	
	public void save(Account account) {
		accounts.put(account.getAccountNumber(), account);
	}

}
