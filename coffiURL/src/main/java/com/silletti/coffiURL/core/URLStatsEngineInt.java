package com.silletti.coffiURL.core;

import java.util.Map;


public interface URLStatsEngineInt {
	
	/**
	 * Method for having the statistics of the shortURL.
	 * @param shortURL
	 * 		shortURL for statistics
	 * @return
	 * 		Statistics for the shortURL
	 * */
	public Map<String, Integer> getStats(String shortURL, Long fromTime, Long toTime);
	
	/**
	 * Method for having the statistics of the shortURL.
	 * @param shortURL
	 * 		shortURL for statistics
	 * @return
	 * 		Statistics for the shortURL
	 * */
	public Map<String, Integer> getStats(String shortURL);
	
}
