package com.bank.BankingSimulator1;

import java.math.BigDecimal;
import java.util.Collection;

import com.bank.BankingSimulator1.exception.AccountNotFoundException;
import com.bank.BankingSimulator1.exception.InvalidAccountException;
import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.repository.AccountRepository;
import com.bank.BankingSimulator1.service.AccountService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AccountRepository repo=new AccountRepository();
       AccountService service= new AccountService(repo);
      try {
		Account acc1= service.createAccount("Riya", "riyak6737@gmail.com", new BigDecimal("2000"));
		Account acc2= service.createAccount("Radha", "radha6737@gmail.com", new BigDecimal("5000"));
		//Account acc3 =service.createAccount("Riya", "riyak6737@gmail.com", new BigDecimal("2000"));
	//	Account acc4= service.createAccount("Radha", "radha6737@gmail.com", new BigDecimal("5000"));
		System.out.println("Created Accounts..");
		System.out.println(acc1);
		System.out.println(acc2);
		System.out.println("---------------------------------------------------------------");
		//get the account based on account number
		Account newAcc1=service.getAccount("1000000");
		System.out.println("Getting the account based on accountNumber");
		System.out.println(newAcc1);
		System.out.println("----------------------------------------------------------------");
		System.out.println("Collecting all accounts...");
		Collection<Account> allAccounts=service.listAll();
		for(Account a:allAccounts) {
			System.out.println(a);
		}
		
	} catch (InvalidAccountException | AccountNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
    }
}
