package Hooks;


import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import utils.ReportLogger;



public class Hooks {
    @BeforeAll
    public static void setup() {
        ReportLogger.initReports();
    }


    @AfterAll
    public static void tearDown() {
        ReportLogger.finalizeReports();
        ReportLogger.logInfo(" Extent Reports Finalized.");
    }
}
