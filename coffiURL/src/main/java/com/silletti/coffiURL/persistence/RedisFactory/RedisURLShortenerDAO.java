package com.silletti.coffiURL.persistence.RedisFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import javax.naming.ldap.UnsolicitedNotificationEvent;

import com.silletti.coffiURL.entities.URLObject;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.DAOException;
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
			handleExceptions(e, ExceptionsHandler.FATAL);
		}
	}
	
	public Boolean createShortURL(String shortURL, URLObject longURL) {
		
		String result = null;
		try {
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
	
				try{
				result = client.hmset("su:"+shortURL, urlProperties);
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
			
		return result.equals(Constants.DAODONE);
	}

	public URLObject getLongURL(String shortURL) {
		
		URLObject result = null;
		try {
			if (shortURL.isEmpty()) {
				return null;
			} else {
						
				try {
					result = new URLObject(client.hget("su:"+shortURL, Constants.LONGURL),
							null,  null, null, null, null, null);	
					System.out.println(result.getURL());
				} catch(DAOException e) {
					handleExceptions(e, ExceptionsHandler.WARNING);
				} finally {
					client.close();
				}
			}
		} catch (NullPointerException e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
			
		if (result.getURL() == null) {
			return null;
		} else {
			return result; 
		}
			
	}
	

	public Boolean updateNumOfClicks(String shortURL) {
		
		Long result = null;
		
		try {
			if (shortURL.isEmpty()) {
				return false;
			} else {
				
				try {
					result = client.hincrBy("su:"+shortURL, Constants.NUMOFCLICKS, 1);
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
		
		return result > 0;
		
	}
	
	public Boolean exist(String shortURL) {
		Boolean flag = true;
		try {
			flag = client.exists("su:"+shortURL);
		} catch (Exception e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
		
		return flag;
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
