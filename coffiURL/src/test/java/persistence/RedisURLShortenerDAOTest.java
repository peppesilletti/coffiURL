package persistence;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.silletti.coffiURL.entities.URLObject;
import com.silletti.coffiURL.persistence.RedisFactory.RedisURLShortenerDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisURLShortenerDAOTest {

	private RedisURLShortenerDAO test;
	private URLObject url;
	private String shortURL;
	
	@Before
	public void setUp() {
		test = new RedisURLShortenerDAO();
		url = new URLObject("www.google.it", "12/12/2015", 
				"Mozilla", "Linux", "Bari/Italy", 5, "127.32.32.652");
	}
	
	@Test
	public void URLShouldBeInsered() {
			Boolean result = test.createShortURL("test6", url);
			assertTrue(result == true);
	}
	
	@Test
	public void DuplicateURLShouldNotBeInsered() {
			Boolean result = test.createShortURL("test1", url);
			assertTrue(result == false);
	}
	
	@Test
	public void ClicksShouldBeUpdate() {
		Boolean result = test.updateNumOfClicks("test1");
		assertTrue(result == true);
	}
	
	@Test
	public void UpdateClicksUrlShouldNotBeEmpty() {
		Boolean result = test.updateNumOfClicks("");
		assertTrue(result == false);
	}
	
	@Test(expected = Exception.class)
	public void UpdateClicksUrlShouldNotBeNull() {
		Boolean result = test.updateNumOfClicks(null);
	}
	
	@Test
	public void SetShortURLShouldNotBeEmpty() {
			Boolean result = test.createShortURL("", url);
			assertTrue(result == false);
	}
	
	@Test(expected = NullPointerException.class)
	public void SetShortURLShouldNotBeNull() {
		Boolean result = test.createShortURL(null, url);
	}
	
	@Test(expected = NullPointerException.class)
	public void SetLongURLShouldNotBeNull() {
		Boolean result = test.createShortURL("test1",null);
	}
	
	
	@Test
	public void GetProperLongURL() {
		URLObject result = test.getLongURL("test1");
		assertTrue(result.getURL().equals("www.googleit"));
	}
	
	@Test 
	public void GiveEmptyShortURL() {
		URLObject result = test.getLongURL("");
		assertNull(result);
	}
	
	
	@Test(expected = Exception.class)
	public void GiveNullShortURL() {
		URLObject result = test.getLongURL(null);
	}
	
}
