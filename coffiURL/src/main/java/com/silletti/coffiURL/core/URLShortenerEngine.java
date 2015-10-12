package com.silletti.coffiURL.core;

import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.URLShortenerEngineException;
import com.silletti.coffiURL.persistence.DAOFactory;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;
import com.silletti.coffiURL.utilities.Blacklist;
import com.silletti.coffiURL.utilities.Chiper;
import com.silletti.coffiURL.utilities.Constants;

import java.util.logging.Level;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Implementation of the interface {@link URLShortenerEngineInt}.
 * */
public class URLShortenerEngine implements URLShortenerEngineInt {

	private URLShortenerDAOInt dao;
	private Blacklist bl;
	
	public URLShortenerEngine() {
		try {
			dao = DAOFactory.getDAOFactory(DAOFactory.REDIS).
					getURLShortenerDAO();
		} catch (Exception e) {
			handleExceptions(e, ExceptionsHandler.FATAL);
		}
		
		bl = new Blacklist();
	}

	public String generateShortURL(String longURL) {
		
		String shortURL = null;
		
		if (!longURL.matches("^(http(s{0,1})://)?(www.)?[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*")) {
			return null;
		} 
		
		if (dao.existLong(longURL)) { //check if for the longURL exist already a public shortURL.
			shortURL = dao.getPublicURL(longURL);
			
			return shortURL;
		} else {
		
			do {
				shortURL = RandomStringUtils.randomAlphabetic(
						Constants.LENGTHSHORTURL);
				
				dao.addShortURL(shortURL, longURL, false);
				
			} while (!dao.existShort(shortURL) || bl.hasBadWord(shortURL));
			
			return shortURL;
		}
	}

	public Boolean createCustomURL(String shortURL, String longURL) {
		
		if (!longURL.matches("^(http(s{0,1})://)?(www.)?[a-zA-Z0-9_/\\-\\.]+\\.([A-Za-z/]{2,5})[a-zA-Z0-9_/\\&\\?\\=\\-\\.\\~\\%]*") 
				|| !shortURL.matches("^[-a-zA-Z0-9+]*$")) {
			return false;
		}
		
		if (bl.hasBadWord(shortURL) || dao.existShort(shortURL)) {
			return false;
		} else {
			Boolean result = dao.addShortURL(shortURL, longURL, true);
			return result;
		}
		
		
	}

	public String getLongURL(String shortURL) {
		
		if (!shortURL.matches("^[-a-zA-Z0-9+]*$")) {
			return null;
		}
		
		if (shortURL.isEmpty()) {
			return null;
		} else {
			if (dao.existShort(shortURL)) {
				return dao.getLongURL(shortURL);
			} else {
				return null;
			}
		}
	}
	
	/**
	 * Method for handling the URLShortenerEngine exceptions.
	 * */
	private void handleExceptions(final Exception e, final Level t) {
		URLShortenerEngineException ex = new URLShortenerEngineException(e.getMessage());
        ExceptionsHandlerInt er = ExceptionsHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

}
