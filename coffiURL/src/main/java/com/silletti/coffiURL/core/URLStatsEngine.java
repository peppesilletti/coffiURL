package com.silletti.coffiURL.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.silletti.coffiURL.entities.Statistics;
import com.silletti.coffiURL.entities.URLInfo;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.URLStatsEngineException;
import com.silletti.coffiURL.persistence.DAOFactory;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;
import com.silletti.coffiURL.persistence.URLStatsDAOInt;
import com.silletti.coffiURL.utilities.Constants;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

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
	
	public Statistics getStats(String shortURL, Long fromTime, Long toTime) {
		
		if (shortURL.isEmpty() || fromTime == null || toTime == null) {
			return null;
		}
		
		if (!shortener.existShort(shortURL)) {
			return null;
		} else {
			List<String> stats = dao.getURLStats(shortURL, fromTime, toTime);
			return aggregateStatistics(stats);
		}
	
	}
	
	public Statistics getStats(String shortURL) {
		if (shortURL.isEmpty()) {
			return null;
		}
		
		if (!shortener.existShort(shortURL)) {
			return null;
		} else {
			List<String> stats = dao.getURLStats(shortURL);
			return aggregateStatistics(stats);
		}
	}

	
	private Statistics aggregateStatistics(List<String> statsList) {
		
		Map<String, Integer> browsers = new HashMap<String, Integer>();
		Map<String, Integer> platforms = new HashMap<String, Integer>();
		Map<String, Integer> locations = new HashMap<String, Integer>();
		Map<String, Integer> others = new HashMap<String, Integer>();
		
		Set<String> ips = new HashSet<String>();
		Statistics stats = new Statistics();
		
		Integer numOfClicks = 0;
		
		for (String s:statsList) {
			Matcher m = Pattern.compile("(\\w+):(\\w+|\\([^)]*\\))").matcher(s);
			
			while (m.find()) {
				
				String key = m.group(1);
				String value = m.group(2);
				
				if (key.equals(Constants.IPADRESS)) {
					ips.add(value);
				}
				
				if (key.equals(Constants.BROWSER)) {
					
					if (browsers.containsKey(value)) {
						int currentValue = browsers.get(value);
						browsers.put(value, currentValue + 1);
					} else {
						browsers.put(value, 1);
					}
					
				} else if (key.equals(Constants.PLATFORM)) {
					
					if (platforms.containsKey(value)) {
						int currentValue = platforms.get(value);
						platforms.put(value, currentValue + 1);
					} else {
						platforms.put(value, 1);
					}
					
				} else if (key.equals(Constants.LOCATION)) {
					
					if (locations.containsKey(value)) {
						int currentValue = locations.get(value);
						locations.put(value, currentValue + 1);
					} else {
						locations.put(value, 1);
					}
					
				}
				
			}
			
			numOfClicks++;
			
		}
		
		others.put("distinctIpAdresses", ips.size());
		others.put(Constants.NUMOFCLICKS, numOfClicks);
		
		stats.addStatistic(Constants.BROWSER, browsers);
		stats.addStatistic(Constants.LOCATION, locations);
		stats.addStatistic(Constants.PLATFORM, platforms);
		stats.addStatistic("others", others);
		
		return stats;
	}
	
	
	public Boolean addStats(String shortURL, URLInfo info) {
		Boolean result = false;
		
		if (shortURL.isEmpty() || shortURL == null
				|| info == null) {
			return false;
		}
		
		if (!shortener.existShort(shortURL)) {
			return false;
		} else {
			return dao.setURLStats(shortURL, info);
		}
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
