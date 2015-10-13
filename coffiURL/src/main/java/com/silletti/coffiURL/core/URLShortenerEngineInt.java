package com.silletti.coffiURL.core;

/**
 * Interface for the URL shortener engine, for handling and 
 * generation of short urls.
 * */
public interface URLShortenerEngineInt {
	
	/**
	 * Method for generating a short URL.
	 * @param longURL
	 * 		longURL associated to the shortURL.
	 * @return
	 * 		generated shortURL.
	 * */
	public String generateShortURL(String longURL);
	
	/**
	 * Method for insering a custom shortURL.
	 * @param longURL
	 * 		longURL associated to the shortURL.
	 * @param shortURL
	 * 		shortURL to associate to the longURL.
	 * @return 
	 * 		result of the operation.
	 * */
	public Boolean createCustomURL(String shortURL, String longURL);
	
	/**
	 * Method for getting the longURL from the database.
	 * @param shortURL
	 * 		shortURL associated to the longURL.
	 * @return
	 * 		shortURL 
	 * */
	public String getLongURL(String shortURL);
}
