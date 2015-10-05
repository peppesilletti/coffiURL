package com.silletti.coffiURL.persistence;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class URLShortenerRiakDAOTest {

	private URLShortenerRedisDAO test;
	
	@Before
	public void setUp() {
		test = new URLShortenerRedisDAO();
	}
	
	@Test
	public void URLShouldBeInsered() {
			Boolean result = test.createShortURL("aeiouy", "www.google.it");
			assertTrue(result == true);
	}
	
	@Test
	public void SetShortURLShouldNotBeEmpty() {
			Boolean result = test.createShortURL("", "www.google.it");
			assertTrue(result == false);
	}
	
	@Test
	public void SetLongURLShouldNotBeEmpty() {
			Boolean result = test.createShortURL("aeiouyi", "");
			assertTrue(result == false);
	}
	
	@Test(expected = NullPointerException.class)
	public void SetShortURLShouldNotBeNull() {
		Boolean result = test.createShortURL(null, "www.google.it");
	}
	
	@Test(expected = NullPointerException.class)
	public void SetLongURLShouldNotBeNull() {
		Boolean result = test.createShortURL("aeiouy",null);
	}
	
	@Test
	public void GetProperLongURL() {
		String result = test.getLongURL("aeiouy");
		assertTrue(result.equals("www.google.it"));
	}
	
	@Test 
	public void GiveEmptyShortURL() {
		String result = test.getLongURL("");
		assertNull(result);
	}
	
	@Test(expected = Exception.class)
	public void GiveNullShortURL() {
		String result = test.getLongURL(null);
	}

}
