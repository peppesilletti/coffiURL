package com.silletti.coffiURL.core;

import com.silletti.coffiURL.entities.URLObject;
import com.silletti.coffiURL.persistence.DAOFactory;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;
import com.silletti.coffiURL.utilities.Blacklist;
import com.silletti.coffiURL.utilities.Constants;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Implementation of the interface {@link URLShortenerEngineInt}.
 * */
public class URLShortenerEngine implements URLShortenerEngineInt {

	private URLShortenerDAOInt dao;
	
	public URLShortenerEngine() {
		try {
			dao = DAOFactory.getDAOFactory(DAOFactory.REDIS).
					getURLShortenerDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String generateShortURL(URLObject longURL) {
		
		String shortURL = null;
		Boolean result = false;
		do {
			shortURL = RandomStringUtils.randomAlphabetic(
					Constants.LENGTHSHORTURL);
			result =  dao.createShortURL(shortURL, longURL);
			
		} while (!dao.exist(shortURL));
		
		if (result == true) {
			return shortURL;
		} else {
			return null;
		}
	}

	public Boolean createCustomURL(String shortURL, URLObject longURL) {
		
		if (Blacklist.hasBadWord(shortURL) || dao.exist(shortURL)) {
			return false;
		} else {
			Boolean result = dao.createShortURL(shortURL, longURL);

			return result;
		}
		
		
	}

	public String getLongURL(String shortURL) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
