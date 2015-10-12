package com.silletti.coffiURL.persistence.RedisFactory;

import java.util.logging.Level;


import com.silletti.coffiURL.entities.URLInfo;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.DAOException;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;
import com.silletti.coffiURL.utilities.Chiper;
import com.silletti.coffiURL.utilities.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Implementation for the interface {@link URLShortenerDAOInt} for the Redis database.
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
	
	public Boolean addShortURL(String shortURL, String longURL, Boolean isCustom) {
		
		String result = null;
		String result2 = null;
		try {
			if (shortURL.isEmpty() || longURL.isEmpty()) {
				return false;
			} else {
				try{
				result = client.set(Chiper.cipher("su:"+shortURL), longURL);
				
				if (!isCustom) {
					result2 = client.set(Chiper.cipher("lu:"+longURL), shortURL);
					if (!result2.equals(Constants.DAODONE)) return false;
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
			
		return result.equals(Constants.DAODONE);
	}

	public String getLongURL(String shortURL) {
		
		String result = null;
		if (shortURL.isEmpty()) {
			return null;
		} else {
			try {
				result = client.get(Chiper.cipher("su:"+shortURL));
			} catch(DAOException e) {
				handleExceptions(e, ExceptionsHandler.WARNING);
			} finally {
				client.close();
			}
		} 
			
		return result;
			
	}
	
	public String getPublicURL(String longURL) {
		
		String result = null;
		if (longURL.isEmpty()) {
			return null;
		} else {
			try {
				result = client.get(Chiper.cipher("lu:"+longURL));
			} catch(DAOException e) {
				handleExceptions(e, ExceptionsHandler.WARNING);
			} finally {
				client.close();
			}
		} 
			
		return result;
	}

	public Boolean updateNumOfClicks(String shortURL) {
		return null;
	}
	
	public Boolean existShort(String shortURL) {
		Boolean flag = true;
		try {
			flag = client.exists(Chiper.cipher("su:"+shortURL));
		} catch (Exception e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
		
		return flag;
	}
	
	public Boolean existLong(String longURL) {
		Boolean flag = true;
		try {
			flag = client.exists(Chiper.cipher("lu:"+longURL));
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
