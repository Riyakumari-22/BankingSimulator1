package com.bank.BankingSimulator1.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReportUtil {
private static final String REPORT_FOLDER="Records";
private static final String REPORT_FILE=REPORT_FOLDER+"/transaction_reports.txt";
static {
	File folder=new File(REPORT_FOLDER);
	if(!folder.exists()) {
		folder.mkdirs();
		
	}
}
public static void writeLine(String Line) {
	try(BufferedWriter br=new BufferedWriter(new FileWriter(REPORT_FILE,true))) {
		br.write(Line);
		br.newLine();
		
	}catch(IOException e) {
		System.out.println("File write error: "+e.getMessage());
	}
}
}

