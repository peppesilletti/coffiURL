package com.silletti.coffiURL.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.silletti.coffiURL.persistence.BlacklistDAOInt;
import com.silletti.coffiURL.persistence.DAOFactory;

/**
 * Class for handling the bad words repository.
 * */
public class Blacklist {

private BlacklistDAOInt dao;
	
	public Blacklist() {
		try {
			dao = DAOFactory.getDAOFactory(DAOFactory.REDIS).
					getBlacklistDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for adding a new bad word in the repository.
	 * @param word
	 * 		The word to be added
	 * @param language 
	 * 		The language of the word
	 * @return
	 * 		The result of the operation.
	 * */
	public Boolean addNewWord(String word) {
		if (!word.matches("^[a-zA-Z]*$")) {
			return false;
		}
		return dao.addWord(word);
	}
	
	/**
	 * Method for checking if one string contains a bad word.
	 * @param string 
	 * 		String to be checked.
	 * @return
	 * 		The result of the operation
	 * */
	public Boolean hasBadWord(String word) {
		if (word.isEmpty()) {
			return false;
		} else {
			List<String> words = dao.getAllWords();
			for (String item:words) {
				if (item.equals(word)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method for initializing the repository of bad words the first time.
	 * */
	public void initRepository() {
		
		List<String> words = new LinkedList<String>();
		File file = new File("badwords.txt");		
				
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			while((line = br.readLine()) != null) {
				if (line.matches("^[a-zA-Z]*$")) { //delete non ASCII characters
					words.add(line);
				}
			}
			
			Collections.sort(words); //sort the list
			
			for(String item:words) {
				dao.addWord(item);
			}
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Blacklist bl = new Blacklist();
		bl.initRepository();
	}
}
