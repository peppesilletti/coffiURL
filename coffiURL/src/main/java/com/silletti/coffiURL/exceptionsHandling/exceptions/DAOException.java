package com.silletti.coffiURL.exceptionsHandling.exceptions;

public class DAOException extends RuntimeException {
    public DAOException(String message) {
        super(message);
    }
}