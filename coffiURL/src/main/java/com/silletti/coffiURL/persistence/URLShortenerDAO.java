package com.silletti.coffiURL.persistence;

/**
 * DAO per l'entità URLShortener. Consente di interfacciare la classe con il livello di persistenza definito.
 * */
public interface URLShortenerDAO {
	
	/**
	 * Metodo per la memorizzazione dello shortURL nel database.
	 * @param shortURL
	 * 			Stringa contenente il shortURL da memorizzare.
	 * @param longURL
	 * 			Stringa contenente il longURL associato allo shortURL.
	 * @return
	 * 			Esito dell'operazione di inserimento.
	 * */
	public Boolean createShortURL(String shortURL, String longURL) ;
	
	/**
	 * Metodo per il recupero del longURL associato ad uno shortURL.
	 * @param
	 * 		Stringa contenente il shortURL associato al longURL
	 * @return
	 * 		Stringa contenente il longURL associato allo shortURL
	 * */
	public String getLongURL(String shortURL);
	
	
}
