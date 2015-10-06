package com.silletti.coffiURL.persistence;

import com.silletti.coffiURL.entities.URLObject;

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
	public Boolean createShortURL(String shortURL, URLObject longURL) ;
	
	/**
	 * Method for getting the longURL associated to a shortURL.
	 * @param
	 * 			String containing the shortURL associated to the longURL.
	 * @return
	 * 		String containing the longUrl associated to the shortURL.
	 * */
	public URLObject getLongURL(String shortURL);
	
	/**
	 * Method for checking if a shortURL is already associated with a longURL.
	 * @param
	 * 		   ShortURL to check
	 * @return
	 * 		   Result of checking.
	 * */
	public Boolean exist(String shortURL);
	
	/**
	 * Metodo per l'update del numero di clicks.
	 * @param shortUrl
	 * 		shortURL per il quale incrementare il numero di clicks
	 * @return
	 * 		Risultato dell'operazione.
	 * */
	public Boolean updateNumOfClicks(String shortURL);
	
	
}
