package com.silletti.coffiURL.exceptionsHandling;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Implements the interface {@link ExceptionHandlerInt}
 * */
public class ExceptionsHandler implements ExceptionsHandlerInt {
	
	/**
	 * The only istance of the class. Singleton	class * */
	private static ExceptionsHandler istance = new ExceptionsHandler();
	
 	private Logger logger;
    private FileHandler handler;
    private SimpleFormatter formatter;
	
    public static ExceptionsHandler getIstance() {
        return istance;
    }
    
    /**
     * Private constructor to build the singleton class.
     * */
    private ExceptionsHandler() {
        if (handler == null) {
            try {
                handler = new FileHandler("log/"+getTimestamp()+".log");
                handler.setLevel(Level.ALL);
                formatter = new SimpleFormatter();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Cannot create"
                        + "the log file");
            }
        }
    }
    
	public void processError(Class c, Throwable exception, Level level) {
	        logger = Logger.getLogger(c.getName());
	        handler.setFormatter(formatter);
	        logger.addHandler(handler);
	        logger.log(level, exception.getMessage(), exception);
	}
	
	public static String getTimestamp() {
		String timeStamp = new SimpleDateFormat("HH:mm:ss - dd.MM.yyyy").format(
				new java.util.Date());
		
		return timeStamp;
	}

}
