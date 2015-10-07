package core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.core.URLShortenerEngine;
import com.silletti.coffiURL.entities.URLObject;
import com.silletti.coffiURL.persistence.URLShortenerDAOInt;

public class URLShortenerEngineTest {

	private URLShortenerEngine test;
	
	private String shortURL = "test"+Math.random()*10;
	
	private URLObject longURL;
	
	@Before
	public void setUp() throws Exception {
		test = new URLShortenerEngine();		
		longURL = new URLObject("www.google.it", "12/12/2012", "Mozilla", 
				"Windows", "Perugia", "0", "172.63.25.120");
	}

	//Test for the shortURL generation method.
	
	@Test
	public void generateShortURLShouldBeInsered() {
		String result = test.generateShortURL(longURL);
		assertTrue(result != null);
	}
	
	//Test for the custom short URL insert
	
	@Test
	public void CustomShortURLShouldBeInsered() {
		Boolean result = test.createCustomURL(shortURL, longURL);
		assertTrue(result);
	}
	
	@Test
	public void CustomShortURLShouldNotContainBadWord() {
		Boolean result = test.createCustomURL("shit", longURL);
		assertTrue(result == false);
	}
	
	

}
