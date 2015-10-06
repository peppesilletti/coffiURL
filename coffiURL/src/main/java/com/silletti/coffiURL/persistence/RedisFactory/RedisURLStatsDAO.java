package com.silletti.coffiURL.persistence.RedisFactory;

import java.util.Map;

import com.silletti.coffiURL.entities.Statistics;
import com.silletti.coffiURL.entities.URLObject;
import com.silletti.coffiURL.persistence.URLStatsDAOInt;
import com.silletti.coffiURL.utilities.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Implementazione dell'interfaccia {@link URLStatsDAOInt} per 
 * il database Redis.
 * */
public class RedisURLStatsDAO implements URLStatsDAOInt {

	private Jedis client;
	
	public RedisURLStatsDAO(){
		try {
			client = new Jedis(Constants.LOCALHOST, Constants.PORT);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		}
	}
	
	public Statistics getURLStats(String shortURL) {
		if (shortURL.isEmpty()) {
			return null;
 		} else {
 			Map<String,String> result = client.hgetAll(shortURL);
 			Statistics stats = new Statistics();
 			stats.setLongUrl(result.get(Constants.LONGURL));
 			stats.setGeoLocation(result.get(Constants.LOCATION));
 			stats.setIpAdress(result.get(Constants.IPADRESS));
 			stats.setNumOfClicks(result.get(Constants.NUMOFCLICKS));
 			stats.setTimestamp(result.get(Constants.TIMESTAMP));
 			stats.setPlatform(result.get(Constants.PLATFORM));
 			stats.setBrowser(result.get(Constants.BROWSER));
 			
 			return stats;
 			
 		}
	}

}
