package persistence;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.entities.URLInfo;
import com.silletti.coffiURL.persistence.RedisFactory.RedisURLStatsDAO;

public class RedisURLStatsDAOTest {

	private RedisURLStatsDAO test;
	private URLInfo stats;
	
	@Before
	public void setUp() throws Exception {
		Date d = new Date();
		test = new RedisURLStatsDAO();	
		stats = new URLInfo("Mozilla", "Linux", "IT", "127.32.32.652");
	}

	@Test
	public void AllURLStatsShouldBeGiven() {
		
		String[] tests = {"browser:Mozilla, ipAdress:127.32.32.652, "
				+ "timestamp:1444299214772, platform:Linux, location:IT",
				"browser:Mozilla, ipAdress:127.32.32.652, "
						+ "timestamp:1444299215340, platform:Linux, location:IT"};
		
		List<String> result = test.getURLStats("test1");
		System.out.println(result);
		for (int i = 0; i < tests.length; i++) {
			System.out.println(tests[i]);
			System.out.println(result.get(i));
			assertTrue(tests[i].equals(result.get(i)));
		}
	}
	
	@Test
	public void RangeURLStatsShouldBeGiven() {
		
		String[] tests = {"browser:Mozilla, ipAdress:127.32.32.652, "
				+ "timestamp:1444299214772, platform:Linux, location:IT"};
		
		List<String> result = test.getURLStats("test1", (long)1, Long.valueOf("1444299214774"));
		System.out.println(result);
		for (int i = 0; i < tests.length; i++) {
			System.out.println(tests[i]);
			System.out.println(result.get(i));
			assertTrue(tests[i].equals(result.get(i)));
		}
	}
	
	@Test
	public void ShortURLShouldNotBeEmpty() {
		List<String> result = test.getURLStats("", (long)123456789, (long)22222222);
		assertNull(result);
	}
	
	//Test for the method setURLStats()
	/*
	@Test
	public void URLStatsShouldBeInsered() {
		Boolean result = test.setURLStats("test1", stats);
		assertTrue(result);
	}*/
	
	
	@Test
	public void URLStatsShouldBeInsered() {
		Boolean result = test.setURLStats("test"+Math.random()*100000, stats);
		assertTrue(result);
	}

}
