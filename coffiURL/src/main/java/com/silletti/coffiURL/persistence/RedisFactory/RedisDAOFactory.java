package com.silletti.coffiURL.persistence.RedisFactory;

import com.silletti.coffiURL.persistence.DAOFactory;
import com.silletti.coffiURL.persistence.URLShortenerDAO;

public class RedisDAOFactory extends DAOFactory {

	@Override
	public URLShortenerDAO getURLShortenerDAO() {
		return (RedisURLShortenerDAO) createDAO(RedisURLShortenerDAO.class);
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
