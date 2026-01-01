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

public class TransactionTest {
   public static void main(String [] args) {
	   AccountRepository accRepo=new AccountRepository();
	   AccountService accService=new AccountService(accRepo);
	   TransactionRepository trxRepo=new TransactionRepository();
	   AlertService alertservice=new AlertService(new BigDecimal("1000"));
	   TransactionService trx= new TransactionService(accService,trxRepo,alertservice);
	   try {
	Account acc1=accService.createAccount("Riya", "riyak6737@gmail.com", new BigDecimal("5000"));
	Account acc2=accService.createAccount("Radha", "radha6737@gmail.com", new BigDecimal("2000"));
	System.out.println(acc1);
	System.out.println(acc2);
	System.out.println("-----------------------------------------------------------------------------------------");
	//trx.Deposit("1000000",new BigDecimal("2000"));
	//System.out.println(acc);
	System.out.println("-----------------------------------------------------------------------------------------");
	//trx.withDraw("1000000", new BigDecimal("1500"));
	trx.Transfer("1000000", "1000001", new BigDecimal("2000"));
	System.out.println(acc1);
	System.out.println(acc2);
	} catch (InvalidAccountException | AccountNotFoundException | InsufficientBalanceException  e) {
		e.printStackTrace();
	}
	  
   }
}
