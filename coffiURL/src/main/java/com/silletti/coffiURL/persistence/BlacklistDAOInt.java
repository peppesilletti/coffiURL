package com.silletti.coffiURL.persistence;

import java.util.List;

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
	 * Method for getting all the bad words in the repository.
	 * @return
	 * 		List with bad words
	 * * */
	public List<String> getAllWords();
	
	/**
	 * Method for saving a list of bad words in the repository.
	 * @param words
	 * 		List of bad words
	 * @return
	 * 		Result of operation
	 * */
	public Boolean setFromList(List<String> words);
	
}
