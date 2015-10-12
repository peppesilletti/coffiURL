package com.silletti.coffiURL.persistence;

import java.util.List;

import com.silletti.coffiURL.entities.URLInfo;
import com.silletti.coffiURL.entities.URLInfo;

/**
 * URLStats entity DAO.
 * */
public interface URLStatsDAOInt {
	
	
	/**
	 * Add statistics for a shortURL.
	 * @param 
	 * 		shortURL 
	 * @return
	 * 		result of operation
	 * */
	public Boolean setURLStats(String shortURL, URLInfo stats);
	
	/**
	 * Get the statistics for an URL.
	 * @param 
	 * 		shortURL shortURL for statistics
	 * 		fromTime min of range
	 * 		toTime	max of range
	 * @return
	 * 		Statistics for the shortURL
	 * */
	public List<String> getURLStats(String shortURL, Long fromTime, Long toTime);
	
	/**
	 * Get the statistics for an URL.
	 * @param 
	 * 		shortURL shortURL for statistics
	 * @return
	 * 		Statistics for the shortURL
	 * */
	public List<String> getURLStats(String shortURL);
}
