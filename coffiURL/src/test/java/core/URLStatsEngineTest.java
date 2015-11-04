package core;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.core.URLStatsEngine;
import com.silletti.coffiURL.entities.Statistics;

public class URLStatsEngineTest {
	
	private URLStatsEngine test;
	private String shortURL = "prova";

	@Before
	public void setUp() throws Exception {
		test = new URLStatsEngine();
	}

	@Test
	public void testGetPeriodStats() {
				
	}

	@Test
	public void testGetAllStats() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddStats() {
		fail("Not yet implemented");
	}

}
