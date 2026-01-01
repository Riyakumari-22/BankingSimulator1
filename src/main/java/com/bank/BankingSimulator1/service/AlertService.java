package com.bank.BankingSimulator1.service;

import java.math.BigDecimal;

import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.util.EmailUtil;

public class AlertService {
     private final BigDecimal thresholdValue;

	 public AlertService(BigDecimal thresholdValue) {
		this.thresholdValue = thresholdValue;
	 }
    public void  checkBalance(Account acc){
    	 if(acc.getBalance().compareTo(thresholdValue)<=0) {
    		 
    		 String subject="Low Balance Alert"+acc.getAccountNumber();
    		 String message="Dear :"+acc.getHolderName()+",\n\nYour account balance is low:"+acc.getBalance()+"\nPlease Maintain minimum balance.";
    	EmailUtil.sendEmail(acc.getEmail(),subject,message);
    		 
    	 }
     }
    public void depositAlert(Account acc,BigDecimal amount) {
 if(acc.getBalance().compareTo(thresholdValue)<=0) {
    		 
    		 String subject="Deposited Amount"+amount;
    		 String message="Dear :"+acc.getHolderName()+",\n\nYour account balance is low:"+acc.getBalance()+"\nPlease Maintain minimum balance.";
    	EmailUtil.sendEmail(acc.getEmail(),subject,message);
    		 
    	 }
    }
}
