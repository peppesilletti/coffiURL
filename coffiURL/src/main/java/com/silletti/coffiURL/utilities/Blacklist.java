package com.silletti.coffiURL.utilities;

/**
 * Class for handling the bad words repository.
 * */
public class Blacklist {

	Blacklist() {}
	
	/**
	 * Method for adding a new bad word in the repository.
	 * @param word
	 * 		The word to be added
	 * @param language 
	 * 		The language of the word
	 * @return
	 * 		The result of the operation.
	 * */
	public static Boolean addNewWord(String word) {
		return true;
	}
	
	/**
	 * Method for checking if one strng contains a bad word.
	 * @param string 
	 * 		String to be checked.
	 * @return
	 * 		The result of the operation
	 * */
	public static Boolean hasBadWord(String string) {
		
		if (string.equals("shit")) {
			return true;
		}
		
		return false;
	}
}
