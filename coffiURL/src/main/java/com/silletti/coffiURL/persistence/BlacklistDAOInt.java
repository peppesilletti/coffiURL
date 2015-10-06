package com.silletti.coffiURL.persistence;

/**
 * Blacklist class DAO.
 * */
public interface BlacklistDAOInt {
	/**
	 * Method for adding a new word in the blacklist repository.
	 * @param word
	 * 		Word to add
	 * @return
	 * 		Result of the operation 
	 * */
	public Boolean addWord(String word);
	
	/**
	 * Method for check if a word is in the repository
	 * @param word
	 * 		Word to check
	 * @return
	 * 		Result of checking
	 * * */
	public Boolean checkWord(String word);
}
