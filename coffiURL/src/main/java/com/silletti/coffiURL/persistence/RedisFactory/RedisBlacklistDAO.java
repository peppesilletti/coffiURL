package com.silletti.coffiURL.persistence.RedisFactory;

import java.util.List;
import java.util.logging.Level;

import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.DAOException;
import com.silletti.coffiURL.persistence.BlacklistDAOInt;
import com.silletti.coffiURL.utilities.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Implementation of the interface {@link BlacklistDAOint}.
 * */
public class RedisBlacklistDAO implements BlacklistDAOInt {

	private Jedis client;
	
	public RedisBlacklistDAO(){
		try {
			client = new Jedis(Constants.LOCALHOST, Constants.PORT);
		} catch (JedisConnectionException e) {
			handleExceptions(e, ExceptionsHandler.FATAL);
		}
	}
	
	public Boolean addWord(String word) {
		
		Boolean result = false;
		
		try {
			if (word.isEmpty()) {
				return false;
			} else {
				try {
				client.lrem("blacklist",1, word);
				result = client.lpush("blacklist", word) > 0;
				} catch(Exception e) {
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
		
		return result;
	}

	public List<String> getAllWords() {
		
		List<String> list = null;
		
		try {
		list = client.lrange("blacklist", 0, -1);
		} catch (Exception e) {
			handleExceptions(e, ExceptionsHandler.WARNING);
		} finally {
			client.close();
		}
		
		if(list != null) {
			return list;
		} else {
			return null;
		}
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
