package WebElements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class log4jWithJunitTutorial {
	private static final Logger log = LogManager.getLogger(log4jWithJunitTutorial.class.getName());
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		log.debug("Debug message log");
		log.error("Error message log");
		log.fatal("Fatal message log");
		
	}

}
