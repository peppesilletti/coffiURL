package com.silletti.coffiURL.persistence;

import com.silletti.coffiURL.entities.Statistics;
import com.silletti.coffiURL.entities.URLObject;

/**
 * DAO per l'entità URLStats. Consente di interfacciare la classe con il livello di persistenza definito.
 * */
public interface URLStatsDAOInt {
	
	/**
	 * Restituisce le statistiche per uno shortURL.
	 * */
	public Statistics getURLStats(String shortURL);
}
