import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class Log4jTest {

    static final Logger logger = Logger.getLogger(Log4jTest.class);

    @Test
    public void sampleLog4jTest() {
        logger.info("INFO: some action was performed");
        logger.debug("DEBUG: the content of data that was transfered, request content");
        logger.error("ERROR: an error occurred that can be fixed");
        logger.fatal("FATAL: error that can't be fixed. Everything crashed.");
    }
}

// import org.testng.log4testng.Logger; - don't use this import, because it doesn't read configuration from log4j.properties

//import org.apache.log4j.Logger;