package com.silletti.coffiURL.core;

import java.util.Map;

import com.silletti.coffiURL.entities.Statistics;


public interface URLStatsEngineInt {
	
	/**
	 * Method for having the statistics of the shortURL.
	 * @param shortURL
	 * 		shortURL for statistics
	 * @return
	 * 		Statistics for the shortURL
	 * */
	public Statistics getStats(String shortURL, Long fromTime, Long toTime);
	
	/**
	 * Method for having the statistics of the shortURL.
	 * @param shortURL
	 * 		shortURL for statistics
	 * @return
	 * 		Statistics for the shortURL
	 * */
	public Statistics getStats(String shortURL);
	
}
