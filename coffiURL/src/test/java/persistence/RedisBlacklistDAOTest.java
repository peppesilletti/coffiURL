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
	
	@Test
	public void WordShouldNotBeEmpty() {
		Boolean result = test.addWord("");
		assertTrue(result == false);
	}

}
