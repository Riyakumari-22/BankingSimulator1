package com.bank.BankingSimulator1.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionRepository {
   public void logTransaction(String type,String account_number,double amount,String target_number){
	 String query ="INSERT INTO transaction(type,account_number,amount,target_account)values(?,?,?,?)";
	 
	 try(Connection conn=DB_Connection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
			 ){
		 pstmt.setString(1, type);
		 pstmt.setString(2,account_number);
		 pstmt.setDouble(3, amount);
		 pstmt.setString(4, target_number);
		 pstmt.executeUpdate();	 
	 }catch(Exception e) {
		 System.out.println("DB Insert Error :"+e.getMessage());
	 }
 }
}
