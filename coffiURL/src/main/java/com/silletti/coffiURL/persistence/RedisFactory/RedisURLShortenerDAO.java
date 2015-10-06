package com.silletti.coffiURL.persistence.RedisFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.ldap.UnsolicitedNotificationEvent;

import com.silletti.coffiURL.entities.URLObject;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;
import com.silletti.coffiURL.utilities.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Implementazione dell'interfaccia {@link URLShortenerDAOInt} per 
 * il database Redis.
 * */
public class RedisURLShortenerDAO implements URLShortenerDAOInt {

	private Jedis client;
	
	public RedisURLShortenerDAO(){
		try {
			client = new Jedis(Constants.LOCALHOST, Constants.PORT);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean createShortURL(String shortURL, URLObject longURL) {
		if (shortURL.isEmpty() || longURL.getURL().isEmpty()) {
			return false;
		} else {
			Map<String, String> urlProperties = 
					new HashMap<String, String>();
			
			urlProperties.put(Constants.LONGURL, longURL.getURL());
			urlProperties.put(Constants.BROWSER, longURL.getBrowser());
			urlProperties.put(Constants.PLATFORM, longURL.getPlatform());
			urlProperties.put(Constants.LOCATION, longURL.getGeoLocation());
			urlProperties.put(Constants.TIMESTAMP, longURL.getTimestamp());
			urlProperties.put(Constants.IPADRESS, longURL.getIpAdress());
			urlProperties.put(Constants.NUMOFCLICKS, "0");

			String result = client.hmset("su:"+shortURL, urlProperties);
			
			return result.equals(Constants.DAODONE);
		}
	}

	public URLObject getLongURL(String shortURL) {
		
		if (shortURL.isEmpty()) {
			return null;
		} else {
					
			URLObject result = new URLObject(client.hget("su:"+shortURL, Constants.LONGURL),
					null,  null, null, null, null, null);	
			
			if (result.getURL() == null) {
				return null;
			} else {
				return result; 
			}
			
		}
	}
	

	public Boolean updateNumOfClicks(String shortURL) {
		if (shortURL.isEmpty()) {
			return false;
		} else {
			Long result = client.hincrBy("su:"+shortURL, Constants.NUMOFCLICKS, 1);
			
			return result > 0;
		}
		
	}
	
	public Boolean exist(String shortURL) {
		Boolean flag = true;

		flag = client.exists("su:"+shortURL);
		
		return flag;
	}

	

}
