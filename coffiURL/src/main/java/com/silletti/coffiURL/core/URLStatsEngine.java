package com.silletti.coffiURL.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.URLStatsEngineException;
import com.silletti.coffiURL.persistence.DAOFactory;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;
import com.silletti.coffiURL.persistence.URLStatsDAOInt;
import com.silletti.coffiURL.utilities.Constants;

/**
 * Implementation of the interface {@link URLStatsEngineInt}.
 * */
public class URLStatsEngine implements URLStatsEngineInt {

	private URLStatsDAOInt dao;
	private URLShortenerDAOInt shortener;
	
	public URLStatsEngine() {
		try {
			dao = DAOFactory.getDAOFactory(DAOFactory.REDIS).
					getURLStatsDAO();
			
			shortener = DAOFactory.getDAOFactory(DAOFactory.REDIS).
					getURLShortenerDAO();
			
		} catch (Exception e) {
			handleExceptions(e, ExceptionsHandler.FATAL);
		}
	}
	
	public Map<String, Integer> getStats(String shortURL, Long fromTime, Long toTime) {
		
		Map<String, Integer> stat = new HashMap<String, Integer>();
		
		if (shortURL.isEmpty() || fromTime == null || toTime == null) {
			return null;
		}
		
		
		if (!shortener.existShort(shortURL)) {
			return null;
		} else {
			List<String> stats = dao.getURLStats(shortURL);

			for (String s:stats) {
				Matcher m = Pattern.compile("(\\w+):(\\w+|\\([^)]*\\))").matcher(s);
				
				while (m.find()) {
					String key = m.group(2);
					if (stat.containsKey(key) 
							&& !key.equals(Constants.TIMESTAMP)
							&& !key.equals(Constants.IPADRESS)) {
						int value = stat.get(key);
						stat.put(key, value + 1);
					} else {
						stat.put(key, 1);
					}
				}	
			}
			
		}
		
		return stat;
	}
	
	public Map<String, Integer> getStats(String shortURL) {
		
		Map<String, Integer> stat = new HashMap<String, Integer>();
		
		if (shortURL.isEmpty()) {
			return null;
		}
		
		//check if URL exist
		if (!shortener.existShort(shortURL)) {
			return null;
		} else {
			List<String> stats = dao.getURLStats(shortURL);

			for (String s:stats) {
				Matcher m = Pattern.compile("(\\w+):(\\w+|\\([^)]*\\))").matcher(s);
				
				while (m.find()) {
					String key = m.group(2);
					if (stat.containsKey(key) 
							&& !key.equals(Constants.TIMESTAMP)
							&& !key.equals(Constants.IPADRESS)) {
						int value = stat.get(key);
						stat.put(key, value + 1);
					} else {
						stat.put(key, 1);
					}
				}
				
			}
		}
		
		return stat;
	}

	/**
	 * Method for handling the URLStatsEngine exceptions.
	 * */
	private void handleExceptions(final Exception e, final Level t) {
        URLStatsEngineException ex = new URLStatsEngineException(e.getMessage());
        ExceptionsHandlerInt er = ExceptionsHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

}
