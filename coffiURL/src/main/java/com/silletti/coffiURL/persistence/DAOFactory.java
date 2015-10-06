package com.silletti.coffiURL.persistence;

import com.silletti.coffiURL.persistence.RedisFactory.RedisDAOFactory;

/**
 * Factory per i Data Accesso Objects. 
 * */
public abstract class DAOFactory {
	
	/**
	 * Costante per selezionare il database Redis.
	 * */
	public static final int REDIS = 1;
	
	/**
	 * Restituisce il DAO per la classe URLShortener.
	 * */
	public abstract URLShortenerDAOInt getURLShortenerDAO();
	
	/**
	 * Restituisce il DAO per la classe URLStats.
	 * */
	public abstract URLStatsDAOInt getURLStatsDAO();
	
	/**
	 * Get the DAO for the class Blacklist.
	 * */
	public abstract BlacklistDAOInt getBlacklistDAO();
	
	/**
     * Restituisce una DAOFactory specifica.
     *
     * @param whichFactory
     *            Tipo della factory richiesta.
     * @return Factory richiesta.
     * */
    public static DAOFactory getDAOFactory(final int whichFactory) {
        switch (whichFactory) {
            case REDIS :
                return new RedisDAOFactory();
            default :
                return null;
        }
    }
}
