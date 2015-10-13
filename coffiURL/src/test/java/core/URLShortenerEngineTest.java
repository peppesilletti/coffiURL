package core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.core.URLShortenerEngine;

public class URLShortenerEngineTest {

	private URLShortenerEngine test;
	
	private String shortURL = "test"+Math.random()*10;
	
	//insert the value from the db
	private String longURL = "xPxOcG";
	
	@Before
	public void setUp() throws Exception {
		test = new URLShortenerEngine();		
	}

	//Test for the shortURL generation method.
	
	@Test
	public void generateShortURLShouldBeInsered() {
		longURL = test.generateShortURL("www.twitter.it");
		assertTrue(longURL != null);
	}
	
	@Test
	public void ExistingPublicURLShouldNotBeInsered() {
		String result = test.generateShortURL("www.twitter.it");
		assertTrue(result.equals(longURL));
	}
	
	//Test for the custom short URL insert
	
	@Test
	public void CustomShortURLShouldBeInsered() {
		Boolean result = test.createCustomURL(shortURL, "www.facebook.it");
		assertTrue(result);
	}
	
	@Test
	public void CustomShortURLShouldNotContainBadWord() {
		Boolean result = test.createCustomURL("shit", "shit");
		assertTrue(result == false);
	}
	
	//Test for getting the longURL
	
	@Test
	public void EmptyShortURLShouldNotWork() {
		String result = test.getLongURL("");
		assertNull(result);
	}
	
	@Test
	public void ShortURLShouldExist() {
		String result = test.getLongURL("xPxOcG");
		assertTrue(result.equals("www.twitter.it"));
	}
	
	@Test
	public void LongURLShouldNotExist() {
		String result = test.getLongURL("pppppppp");
		assertNull(result);
	} 
	
	

}
