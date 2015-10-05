package com.silletti.coffiURL.persistence;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Implementazione dell'interfaccia {@link URLShortenerDAO} per 
 * il database Redis.
 * */
public class URLShortenerRedisDAO implements URLShortenerDAO {

	private Jedis client;
	
	public URLShortenerRedisDAO(){
		try {
			client = new Jedis("localhost", 6379);
		} catch (JedisConnectionException e) {
			//gestione eccezione
		}
	}
	
	public Boolean createShortURL(String shortURL, String longURL) {
		if (shortURL.isEmpty() || longURL.isEmpty()) {
			return false;
		}
		else {
			String result = client.set(shortURL, longURL);
			if (result.equals("OK")) {
				return true;
			} else {
				return false;
			}
		}
	}

	public String getLongURL(String shortURL) {
		String result = client.get(shortURL);
		return result;
	}

}
