package com.silletti.coffiURL.exceptionsHandling;

import java.util.logging.Level;

/**
 * Exceptions handlers class. Contains the levels constants for the 
 * type of exception.
 * */
public interface ExceptionsHandlerInt {
	/**
     * Type of exceptions that stops the system.
     * */
    Level FATAL = Level.SEVERE;
    
    /**
     * Type of exceptions that doesn't stop the system.
     * */
    Level WARNING = Level.WARNING;
    
    /**
     * Low importance type of exceptions. 
     * No error is shown.
     * */
    Level NO_MESSAGE = Level.INFO;
    
   
    /**
     * Log what is happened at the system.
     * @param c
     *      Class that threw the exception.
     * @param exception
     *      Type of exceptions
     * @param level
     *      Severity level of the exception
     * */
    void processError(Class c, Throwable exception,
            Level level);
}
