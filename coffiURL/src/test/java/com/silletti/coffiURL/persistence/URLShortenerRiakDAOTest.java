package com.silletti.coffiURL.persistence;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class URLShortenerRiakDAOTest {

	private URLShortenerRiakDAO test;
	
	@Before
	public void setUp() {
		test = new URLShortenerRiakDAO();
	}
	
	@Test
	public void URLShouldBeInsered() {
			Boolean result = test.createShortURL("aeiouy", "www.google.it");
			assertTrue(result == true);
	}
	
	@Test
	public void shortURLShouldNotBeEmpty() {
			Boolean result = test.createShortURL("", "www.google.it");
			assertTrue(result == false);
	}
	
	@Test
	public void longURLShouldNotBeEmpty() {
			Boolean result = test.createShortURL("aeiouyi", "");
			assertTrue(result == false);
	}
	
	@Test(expected = NullPointerException.class)
	public void shortURLShouldNotBeNull() {
		Boolean result = test.createShortURL(null, "www.google.it");
	}
	
	@Test(expected = NullPointerException.class)
	public void longURLShouldNotBeNull() {
		Boolean result = test.createShortURL("aeiouy",null);
	}

}
