package com.silletti.coffiURL.core;

import com.silletti.coffiURL.entities.URLObject;

public interface URLStatsEngineInt {
	
	/**
	 * Method for having the statistics of the shortURL.
	 * @param shortURL
	 * 		shortURL for statistics
	 * @return
	 * 		Statistics for the shortURL
	 * */
	public String getURLStats(URLObject shortURL);
}
