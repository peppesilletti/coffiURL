package com.silletti.coffiURL.persistence;

import com.silletti.coffiURL.entities.Statistics;

/**
 *  * URLShortener DAO. 
 * */
public interface URLShortenerDAOInt {
	
	/**
	 * Method for storing the shortURL and its informations in the DB.
	 * @param shortURL
	 * 			String containing the url to store.
	 * @param longURL
	 * 			String containing the longUrl associated to the shortURL.
	 * @return
	 * 			Result of operation.
	 * */
	public Boolean addShortURL(String shortURL, String longURL, Boolean isCustom) ;
	
	/**
	 * Method for getting the longURL associated to a shortURL.
	 * @param
	 * 			String containing the shortURL associated to the longURL.
	 * @return
	 * 		String containing the longUrl associated to the shortURL.
	 * */
	public String getLongURL(String shortURL);
	
	/**
	 * Method for getting the public shortURL for a longURL.
	 * @param
	 * 			String containing the longURL associated to the shortURL.
	 * @return
	 * 		String containing the shortURL associated to the longURL.
	 * */
	public String getPublicURL(String longURL);
	
	/**
	 * Method for checking if a shortURL is already associated with a longURL.
	 * @param
	 * 		   ShortURL to check
	 * @return
	 * 		   Result of checking.
	 * */
	public Boolean existShort(String shortURL);
	
	/**
	 * Method for checking if random shortURL is associated to a longURL
	 * @param
	 * 		   longURL to check
	 * @return
	 * 		   Result of checking.
	 * */
	public Boolean existLong(String longURL);
	
	/**
	 * Update number of clicks.
	 * @param shortUrl
	 * @return
	 * */
	public Boolean updateNumOfClicks(String shortURL);
	
	
	
	
}
