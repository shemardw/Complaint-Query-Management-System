/*package testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// import the logging

public class LoggingTest {

	// create logger
	private static Logger demologger = LogManager.getLogger(LoggingTest.class);
	
	public static void main(String[] args) {
		// print to console
		System.out.printf("Hello World");
		
		// print to file
		demologger.info("Click successful");
		demologger.error("Failed");
		demologger.debug("This is debug");
	}

}*/

package testing; 

import java.util.logging.FileHandler; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.util.logging.SimpleFormatter; 

public class LoggingTest {
	private static Logger Logger = Logger.getLogger("My Log"); 
	public static void init(){
		FileHandler fh; 
		try{
			//fh = new FileHandler("C:/MyLogFile.log",true); 
			fh = new FileHandler(System.getProperty("user.dir")+System.getProperty("file separator")+"MyLogFile.log", true); //to append log files 
			Logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter(); 
			fh.setFormatter(formatter); 

			Logger.info("Logger Initialized."); 
		} catch (Exception e){
			Logger.log(Level.WARNING,"Exception ::", e);
		}
	}

public static void writeToLog(String msg){
	Logger.info(msg);
}
public static void writeToLog(String msg, Exception exp){
	Logger.log(Level.WARNING,msg,exp);
}

public static void main(String[]args){
	init(); 
	try{
		int a = 10/0; //arithmetic exception 
	} 
	catch (Exception e){
		Logger.log(Level.WARNING, "Exception ::", e); 
	}
	Logger.info("End of Program."); 


