package com.silletti.coffiURL.core;

public interface URLShortener {
	
	/**
	 * Metodo per la generazione di uno shortURL.
	 * @param longURL
	 * 		longURL a cui associare il shortURL.
	 * @return
	 * 		shortURL generato.
	 * */
	public String generateShortURL(String longURL);
}
