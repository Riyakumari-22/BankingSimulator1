package com.bank.BankingSimulator1.util;


import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailUtil {
    private static final String FROM_EMAIL="riyak6737@gmail.com";
    private static final String APP_PASSWORD="tgix quxz zqcn vuwd";
    public static void sendEmail(String to_email,String subject,String body){
    	Properties props=new Properties();
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.host","smtp.gmail.com");
    	props.put("mail.smtp.port","587");
    	Session session=Session.getInstance(props,new  Authenticator() {
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(FROM_EMAIL,APP_PASSWORD);
    		}


    	});
    try {
    MimeMessage message=new MimeMessage(session);
    message.setFrom(new InternetAddress(FROM_EMAIL));
    message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to_email));
    message.setSubject(subject);
    message.setText(body);
    Transport.send(message);
    System.out.println("Email send to : "+to_email);
    }catch(MessagingException e) {
    	System.out.println("Email Error "+e.getMessage());
    }
    }
}
