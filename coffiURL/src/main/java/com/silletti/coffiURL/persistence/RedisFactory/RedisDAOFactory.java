package com.silletti.coffiURL.persistence.RedisFactory;

import java.util.logging.Level;

import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;
import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandlerInt;
import com.silletti.coffiURL.exceptionsHandling.exceptions.DAOException;
import com.silletti.coffiURL.persistence.BlacklistDAOInt;
import com.silletti.coffiURL.persistence.DAOFactory;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;
import com.silletti.coffiURL.persistence.URLStatsDAOInt;

public class RedisDAOFactory extends DAOFactory {

	@Override
	public URLShortenerDAOInt getURLShortenerDAO() {
		return (RedisURLShortenerDAO) createDAO(RedisURLShortenerDAO.class);
	}
	
	@Override
	public URLStatsDAOInt getURLStatsDAO() {
		return (RedisURLStatsDAO) createDAO(RedisURLStatsDAO.class);
	}
	
	@Override
	public BlacklistDAOInt getBlacklistDAO() {
		return (BlacklistDAOInt) createDAO(RedisBlacklistDAO.class);
	}
	
	 /**
     * Ritorna un DAO specifico per un'entit�.
     *
     * @param classObj
     *            La classe del DAO da ritornare.
     * @return Il DAO richiesto.
     * @throws DAOException
     *             Solleva un'eccezione se non è possibile istanziare il DAO.
     * */
    private Object createDAO(final Class classObj) {
        try {
            return classObj.newInstance();
        } catch (Exception e) {
        	handleExceptions(e, ExceptionsHandler.FATAL);
        }
        return null;
    }
    
    /**
	 * Method for handling the DAO exceptions.
	 * */
	private void handleExceptions(final Exception e, final Level t) {
        DAOException ex = new DAOException(e.getMessage());
        ExceptionsHandlerInt er = ExceptionsHandler.getIstance();
        er.processError(ex.getClass(), ex, t);
    }

}
