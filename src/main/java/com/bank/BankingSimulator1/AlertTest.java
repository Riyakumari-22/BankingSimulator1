package com.bank.BankingSimulator1;

import java.math.BigDecimal;

import com.bank.BankingSimulator1.exception.AccountNotFoundException;
import com.bank.BankingSimulator1.exception.InsufficientBalanceException;
import com.bank.BankingSimulator1.exception.InvalidAccountException;
import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.repository.AccountRepository;
import com.bank.BankingSimulator1.repository.TransactionRepository;
import com.bank.BankingSimulator1.service.AccountService;
import com.bank.BankingSimulator1.service.AlertService;
import com.bank.BankingSimulator1.service.TransactionService;

public class AlertTest {
   public static void main(String [] args) {
	   AccountRepository repo=new AccountRepository();
	   AccountService acc=new AccountService(repo);
	   TransactionRepository trxRepo=new TransactionRepository();
	   AlertService alert=new AlertService(new BigDecimal("1000"));
	   TransactionService trx=new TransactionService(acc,trxRepo,alert);
	/*try{
		Account account= acc.createAccount("Riya", "riyak6737@gmail.com", new BigDecimal("5000"));
		trx.withDraw(account.getAccountNumber(), new BigDecimal("500"));
		System.out.println("Amount is withdrawn Successfully..");
		System.out.println("Total Balance: "+account.getBalance());
	} catch (InvalidAccountException | AccountNotFoundException | InsufficientBalanceException e) {
		e.printStackTrace();
	}*/
	try {                                                                                             
  	Account account= acc.createAccount("krishna", "harikrishnahh403@gmail.com",new BigDecimal("1000"));
    	trx.withDraw(account.getAccountNumber(), new BigDecimal("200"));                                
      	System.out.println("Amount is withdrawn Successfully..");                                        
      	System.out.println("Total Balance: "+account.getBalance());                                      
      } catch (InvalidAccountException | AccountNotFoundException | InsufficientBalanceException e) {   
      	e.printStackTrace();                                                                             
      }  
   }
}