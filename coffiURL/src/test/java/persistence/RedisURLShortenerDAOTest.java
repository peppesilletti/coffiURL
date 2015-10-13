package persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.silletti.coffiURL.persistence.RedisFactory.RedisURLShortenerDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisURLShortenerDAOTest {

	private RedisURLShortenerDAO test;
	
	@Before
	public void setUp() {
		test = new RedisURLShortenerDAO();
	}
	
	//Test per il metodo createShortURL
	
	@Test
	public void SetShortURLShouldNotBeEmpty() {
			Boolean result = test.addShortURL("", "www.facebook.it", true);
			assertTrue(result == false);
	}
	
	@Test(expected = NullPointerException.class)
	public void SetShortURLShouldNotBeNull() {
		Boolean result = test.addShortURL(null, "www.facebook.it", true);
	}
	
	@Test(expected = NullPointerException.class)
	public void SetLongURLShouldNotBeNull() {
		Boolean result = test.addShortURL("test1", null, true);
	}
	
	@Test
	public void CustomURLShouldBeInsered() {
			Double rand = Math.random()*100000;
			Boolean result = test.addShortURL("test"+rand, "www.facebook.it", true);
			assertTrue(result == true);
	}
	
	@Test
	public void RandomURLShouldBeInsered() {
			Double rand = Math.random()*100000;
			Boolean result = test.addShortURL("test"+rand, "www.facebook.it", false);
			assertTrue(result == true);
	}
	
	//Test per il metodo getLongURL	
	
	@Test
	public void GetProperLongURL() {
		String result = test.getLongURL("test1");
		assertTrue(result.equals("www.google.it"));
	}
	
	@Test 
	public void GiveEmptyShortURL() {
		String result = test.getLongURL("");
		assertNull(result);
	}
	
	
	@Test
	public void GiveNullShortURL() {
		String result = test.getLongURL(null);
		assertNull(result);
	}
	
	@Test
	public void ShortURLShouldExist() {
		String result = test.getLongURL(Double.toString(Math.random()*10));
		assertNull(result);
	}
}
