package com.silletti.coffiURL.persistence.RedisFactory;

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
            e.printStackTrace();
        }
        return null;
    }

}
