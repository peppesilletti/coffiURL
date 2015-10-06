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
		} else if (existKey(shortURL)) {
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

			String result = client.hmset(shortURL, urlProperties);
			
			return result.equals(Constants.DAODONE);
		}
	}

	public URLObject getLongURL(String shortURL) {
		
		if (shortURL.isEmpty()) {
			return null;
		} else {
					
			URLObject result = new URLObject(client.hget(shortURL, Constants.LONGURL),
					null,  null, null, null, null, null);	
			
			if (result.getURL() == null) {
				return null;
			} else {
				return result; 
			}
			
		}
	}
	
	/**
	 * Metodo per l'update del numero di clicks.
	 * @param shortUrl
	 * 		shortURL per il quale incrementare il numero di clicks
	 * @return
	 * 		Risultato dell'operazione.
	 * */
	public Boolean updateNumOfClicks(String shortURL) {
		if (shortURL.isEmpty()) {
			return false;
		} else {
			Long result = client.hincrBy(shortURL, Constants.NUMOFCLICKS, 1);
			
			return result > 0;
		}
		
	}
	
	/**
	 * Metodo per la verifica delle chiavi duplicate.
	 * @param key
	 * 		Chiave da verificare.
	 * @¶eturn
	 * 		Risultato della verifica (true/false)
	 * */
	private Boolean existKey(String shortURL) {
		Boolean flag = true;

		flag = client.exists(shortURL.getBytes());
		
		return flag;
	}
	

}
