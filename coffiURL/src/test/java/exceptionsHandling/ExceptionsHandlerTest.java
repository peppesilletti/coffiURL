package exceptionsHandling;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.silletti.coffiURL.exceptionsHandling.ExceptionsHandler;

public class ExceptionsHandlerTest {

	private ExceptionsHandler test;
	
	@Before
	public void setUp() throws Exception {
		test = ExceptionsHandler.getIstance();
	}
	
	@Test
	public void LogFileShouldBeCreated() {
		test.processError(ExceptionsHandler.class, new NullPointerException(), ExceptionsHandler.WARNING);
		File logFile = new File("log/"+ExceptionsHandler.getTimestamp()+".log");
		assertTrue(logFile.exists());
	}

}
