package core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.utilities.Blacklist;

public class BlacklistTest {

	private Blacklist test;
	
	@Before
	public void setUp() throws Exception {
		test = new Blacklist();
	}

	@Test
	public void AddInvalidWordShouldNotWork() {
		String[] tries = {"@", "ç", "ciao ciao", "à", "{", "-"};
		for (int i = 0; i < tries.length; i++) {
			Boolean result = test.addNewWord(tries[i]);
			assertTrue(result == false);
		}
	}
	
	@Test
	public void AddNewWord() {
		Boolean result = test.addNewWord("cavolo");
		assertTrue(result);
	}

	//Test per checkWord()
	@Test
	public void CheckedWordShouldNotBeEmpty() {
		Boolean result = test.hasBadWord("");
		assertTrue(result == false);
	}
	
	@Test
	public void CheckedWordIsInside() {
		Boolean result = test.hasBadWord("welzijnsmafia");
		assertTrue(result == true);
	}
	
	@Test
	public void CheckedWordIsNotInside() {
		Boolean result = test.hasBadWord("aeiouy");
		assertTrue(result == false);
	}

}
