package persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.persistence.RedisFactory.RedisBlacklistDAO;

public class RedisBlacklistDAOTest {

	private RedisBlacklistDAO test;
	
	@Before
	public void setUp() throws Exception {
		test = new RedisBlacklistDAO();
	}

	//Test per addWord()
	@Test
	public void WordShouldBeInsered() {
		Boolean result = test.addWord("abcde");
		assertTrue(result);
	}
	
	@Test(expected=Exception.class) 
	public void WordShouldNotBeInsered(){
		test.addWord(null);
	}
	
	@Test
	public void WordShouldNotBeEmpty() {
		Boolean result = test.addWord("");
		assertTrue(result == false);
	}
	
	//Test per checkWord()
	@Test
	public void CheckedWordShouldNotBeEmpty() {
		Boolean result = test.checkWord("");
		assertTrue(result == false);
	}
	
	@Test
	public void CheckedWordIsInside() {
		Boolean result = test.checkWord("abcd");
		assertTrue(result == true);
	}
	
	@Test
	public void CheckedWordIsNotInside() {
		Boolean result = test.checkWord("aeiouy");
		assertTrue(result == false);
	}

}
