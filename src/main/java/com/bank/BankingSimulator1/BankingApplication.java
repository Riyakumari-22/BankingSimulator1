package com.bank.BankingSimulator1;

import java.math.BigDecimal;
import java.util.Scanner;

import com.bank.BankingSimulator1.exception.AccountNotFoundException;
import com.bank.BankingSimulator1.exception.InsufficientBalanceException;
import com.bank.BankingSimulator1.exception.InvalidAccountException;
import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.repository.AccountRepository;
import com.bank.BankingSimulator1.repository.TransactionRepository;
import com.bank.BankingSimulator1.service.AccountService;
import com.bank.BankingSimulator1.service.AlertService;
import com.bank.BankingSimulator1.service.TransactionService;


public class BankingApplication {
    public static void main(String [] args) {
	Scanner sc=new Scanner(System.in);
	Account acc=new Account("Riya","riyak6737@gmail.com",new BigDecimal("1000"));
	AccountRepository accRepo=new AccountRepository();
	AccountService accService =new AccountService(accRepo);
	TransactionRepository trxRepo =new TransactionRepository();
	AlertService alertService=new AlertService(new BigDecimal("1000"));
	TransactionService trxService=new TransactionService(accService,trxRepo,alertService);
	System.out.println("======================================================================================");
	System.out.println("                           WELCOME TO BANK SIMULATION APPLICATION                     ");
	System.out.println("======================================================================================");
	boolean running=true;
	while(running) {
		System.out.println("\nChoose an option");
		System.out.println("1.Create Account");
		System.out.println("2..Deposit");
		System.out.println("3.Withdraw");
		System.out.println("4.Transfer");
		System.out.println("5.Account Details");
		System.out.println("6.List All Accounts");
		System.out.println("7.Exit");
		System.out.println("Enter Choice: ");
		int choice=sc.nextInt();
		sc.nextLine();
		switch(choice) {
		case 1:
			System.out.println("Enter Name:");
			String name=sc.nextLine();
			System.out.println("Enter Email:");
			String email=sc.nextLine();
			System.out.println("Enter Opening Balance:");
			BigDecimal openingBalance=sc.nextBigDecimal();
			try {
			Account account=accService.createAccount(name, email, openingBalance);
			System.out.println("Account created successfully:");
			System.out.println("Account Number :"+account.getAccountNumber());
			} catch (InvalidAccountException e) {
			
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("Enter Account number: ");
			String accNumber=sc.nextLine();
			System.out.println("Enter Amount to Deposit");
			BigDecimal amount=sc.nextBigDecimal();
			try {
				trxService.Deposit(accNumber, amount);
				System.out.println("Amount Deposited successfully: "+amount);
			} catch (InvalidAccountException | AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter Account number: ");
			String accNumber1=sc.nextLine();
			System.out.println("Enter Amount to WithDraw");
			BigDecimal amount1=sc.nextBigDecimal();
			try {
				trxService.withDraw(accNumber1, amount1);
				System.out.println("Amount Withdrawn successfully: "+amount1);
			} catch (InvalidAccountException | AccountNotFoundException | InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     break;
		case 4:
			System.out.println("Enter Account number of sender: ");
			String sender=sc.nextLine();
			System.out.println("Enter Account number of reciever: ");
			String reciever=sc.nextLine();
			System.out.println("Enter Amount to Transfer");
			BigDecimal amnt=sc.nextBigDecimal();
			try {
				trxService.Transfer(sender, reciever, amnt);
				System.out.println("Amount transfered successfully:"+amnt);
			} catch (AccountNotFoundException | InsufficientBalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Enter Account Number to get the Details:");
			String accNo=sc.nextLine();
			try {
				Account account=accService.getAccount(accNo);
				System.out.println("Account Details: "+account);
			} catch (AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("Listing All accounts: ");
			for(Account x:accService.listAll()) {
				System.out.println(x);
			}
			break;
		case 7: 
			System.out.println("Thank you for using our Banking Application");
			running=false;
			break;
		default:
			System.out.println("Invalid Choice. Please Enter Valid Choice");
		}
	}
	
    }
}
