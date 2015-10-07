package com.silletti.coffiURL.persistence.RedisFactory;

import java.util.Map;
import java.util.logging.Level;

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
	
	public Statistics getURLStats(String shortURL) {
		
		Statistics stats = null;
		Map<String,String> result = null;
		
		try {
			if (shortURL.isEmpty()) {
				return null;
	 		} else {
	 			try {
	 				result = client.hgetAll("su:"+shortURL);
	 			} catch(DAOException e) {
	 				handleExceptions(e, ExceptionsHandler.WARNING);
	 			} finally {
	 				client.close();
	 			}
	 			stats = new Statistics();
	 			stats.setLongUrl(result.get(Constants.LONGURL));
	 			stats.setGeoLocation(result.get(Constants.LOCATION));
	 			stats.setIpAdress(result.get(Constants.IPADRESS));
	 			stats.setNumOfClicks(result.get(Constants.NUMOFCLICKS));
	 			stats.setTimestamp(result.get(Constants.TIMESTAMP));
	 			stats.setPlatform(result.get(Constants.PLATFORM));
	 			stats.setBrowser(result.get(Constants.BROWSER));
	 		}
		} catch(NullPointerException e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
 		
		return stats;
 			
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
