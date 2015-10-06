package com.silletti.coffiURL.persistence.RedisFactory;

import java.util.List;

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
			e.printStackTrace();
		}
	}
	
	public Boolean addWord(String word) {
		if (word.isEmpty()) {
			return false;
		} else {
			client.lrem("blacklist",1, word);
			return client.lpush("blacklist", word) > 0;
			
		}
	}

	public List<String> getAllWords() {
		List<String> list = client.lrange("blacklist", 0, -1);
		
		if(list != null) {
			return list;
		} else {
			return null;
		}
	}

	public Boolean setFromList(List<String> words) {
		
		return null;
	}

}
