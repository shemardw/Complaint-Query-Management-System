package testing;

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

}
