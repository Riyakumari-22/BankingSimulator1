package com.bank.BankingSimulator1;

import java.math.BigDecimal;

import com.bank.BankingSimulator1.model.Account;
import com.bank.BankingSimulator1.repository.AccountRepository;
import com.bank.BankingSimulator1.repository.TransactionRepository;
import com.bank.BankingSimulator1.service.AccountService;
import com.bank.BankingSimulator1.service.AlertService;
import com.bank.BankingSimulator1.service.TransactionService;
import com.google.gson.Gson;
import static spark.Spark.*;

public class APIServer {
    public static void main(String [] args) {
    	port(8080);
    	enableCORS();
        Gson gson=new Gson();
    	AccountRepository accRepo=new AccountRepository();
    	AccountService accService=new AccountService(accRepo);
    	TransactionRepository trxRepo=new TransactionRepository();
    	AlertService alertService=new AlertService(new BigDecimal("1000"));
    	TransactionService trxService=new TransactionService(accService,trxRepo,alertService);
    	System.out.println("Spark Server started on port number 8080");
    	//createAccount API
    	post("/accounts/create",(req,res)->{
    		System.out.println("/accounts/create  API is called..");
    		res.type("application/json");
    		AccountRequest data=gson.fromJson(req.body(),AccountRequest.class);
    		Account acc=accService.createAccount(data.Name,data.Email,data.Balance);
    		return gson.toJson(acc);
    		
    	});
    	//deposit API
    	post("/Transactions/Deposit",(req,res) ->{
    		System.out.println("/Transactions/Deposit API is called..");
    	TrxRequest data=gson.fromJson(req.body(),TrxRequest.class);
    	trxService.Deposit(data.accNo, data.Amount);
    	return "Deposit Successful..!!";
    	});
    	//withdraw API
    	post("/Transactions/withDraw",(req,res) ->{
    		System.out.println("/Transactions/withDraw API is called..");
    	TrxRequest data=gson.fromJson(req.body(),TrxRequest.class);
    	trxService.withDraw(data.accNo, data.Amount);
    	return "withDraw Successful..!!";
    	});
       // transfer API
    	post("/Transactions/transfer",(req,res)->{
    	System.out.println("/Transactions/transfer API is called...");
    	TransferRequest data=gson.fromJson(req.body(),TransferRequest.class);
    	trxService.Transfer(data.Sender_accNo, data.Reciever_accNo, data.Amount);
    	return "Transfer successful...!!";
    	});
    }
    public static void enableCORS(){
    	options("/*",(request,response)->{
    		String reqheaders=request.headers("Access-Control-Headers");
    		if(reqheaders !=null) {
    			response.header("Access-Control-Allow-Headers",reqheaders);
    		}
    		return "OK";
    	});
    	before((request,response)->{
    	response.header("Access-Control-Allow-Origin","*");
    	response.header("Access-Control-Allow-Methods","GET,POST,DELETE,OPTIONS,PUT");
    	response.header("Access-Control-Allow-Headers","Content-Type,Authorization");
    	});
    }
	static class AccountRequest{
		String  Name ;
		String Email ;
		BigDecimal Balance;
	}
	static class TrxRequest{
		String  accNo;
		BigDecimal Amount;
	}
	static class TransferRequest{
		String Sender_accNo;
		String Reciever_accNo;
		BigDecimal Amount;
	}

}
