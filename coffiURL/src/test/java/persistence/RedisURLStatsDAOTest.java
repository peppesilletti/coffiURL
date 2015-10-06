package persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.entities.Statistics;
import com.silletti.coffiURL.persistence.RedisFactory.RedisDAOFactory;
import com.silletti.coffiURL.persistence.RedisFactory.RedisURLStatsDAO;

public class RedisURLStatsDAOTest {

	private RedisURLStatsDAO test;
	
	@Before
	public void setUp() throws Exception {
		test = new RedisURLStatsDAO();		
	}

	@Test
	public void URLStatsShouldBeGiven() {
		Statistics result = test.getURLStats("test1");
		assertTrue(result.getLongUrl().equals("www.googleit"));
	}
	
	@Test
	public void ShortURLShouldNotBeEmpty() {
		Statistics result = test.getURLStats("");
		assertNull(result);
	}
	
	@Test(expected=Exception.class)
	public void ShortURLShouldNotBeNull() {
		Statistics result = test.getURLStats(null);
	}

}
