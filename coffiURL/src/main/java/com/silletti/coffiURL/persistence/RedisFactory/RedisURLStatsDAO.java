package com.silletti.coffiURL.persistence.RedisFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.silletti.coffiURL.entities.Statistics;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.DAOException;
import com.silletti.coffiURL.persistence.URLStatsDAOInt;
import com.silletti.coffiURL.utilities.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Implementation of the interface {@link URLStatsDAOInt}.
 * */
public class RedisURLStatsDAO implements URLStatsDAOInt {

	private Jedis client;
	
	public RedisURLStatsDAO(){
		try {
			client = new Jedis(Constants.LOCALHOST, Constants.PORT);
		} catch (JedisConnectionException e) {
			handleExceptions(e, ExceptionsHandler.FATAL);
		}
	}
	
	public List<String> getURLStats(String shortURL, Long fromTime, Long toTime) {
		
		List<String> stats = new LinkedList<String>();
		
		try {
			if (shortURL.isEmpty()) {
				return null;
	 		} else {
	 			try {		
	 				
	 				Set<String> items =
	 						client.zrangeByScore("stats:"+shortURL, fromTime, toTime);
	 			
	 				for (String item:items) {
	 					stats.add(item);
	 				}
	 				
	 			} catch(DAOException e) {
	 				handleExceptions(e, ExceptionsHandler.WARNING);
				} finally {
	 				client.close();
	 			}
	 		}
			
		} catch(NullPointerException e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
 		
		return stats;
 			
	}
	
	public List<String> getURLStats(String shortURL) {
		List<String> stats = new LinkedList<String>();
		
		try {
			if (shortURL.isEmpty()) {
				return null;
	 		} else {
	 			try {		
	 				
	 				Set<String> items =
	 						client.zrangeByScore("stats:"+shortURL, "-inf", "+inf");
	 			
	 				for (String item:items) {
	 					stats.add(item);
	 				}
	 				
	 			} catch(DAOException e) {
	 				handleExceptions(e, ExceptionsHandler.WARNING);
				} finally {
	 				client.close();
	 			}
	 		}
			
		} catch(NullPointerException e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
 		
		return stats;
	}
	
	public Boolean setURLStats(String shortURL, Statistics stats) {
		Long result = null;
		Long result2 = null;
		
		try {
			if (shortURL == null) {
				return false;
			} else {
					
				try{
					Date date = new Date();
					Map<String,Double> s = new HashMap<String,Double>();
					
					s.put(stats.toString(), (double)date.getTime());
					
					result = client.zadd("stats:"+shortURL, s);
					//increment number of clicks
					result2 = client.incr("su:"+shortURL+":clicks");
					
				} catch(DAOException e) {
					handleExceptions(e, ExceptionsHandler.WARNING);
				} finally {
					client.close();
				}
			}
		} catch(NullPointerException e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
			
		return result > 0 && result2 > 0;
	}
	
	/**
	 * Method for handling the DAO exceptions.
	 * */
	private void handleExceptions(final Exception e, final Level t) {
        DAOException ex = new DAOException(e.getMessage());
        ExceptionsHandlerInt er = ExceptionsHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

	



}
