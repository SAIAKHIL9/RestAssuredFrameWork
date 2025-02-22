package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportLogger {
    private static final Logger logger = LogManager.getLogger(ReportLogger.class);
    private static ExtentReports extent;
    private static ExtentTest test;


    public static void initReports() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            logger.info(" Extent Reports Initialized.");
        }
    }

    public static void startTest(String testName) {
        if (extent == null) {
            initReports();
        }
        test = extent.createTest(testName);
        logger.info(" Test Started: " + testName);
    }


    public static void logInfo(String message) {
        test.info(message);
        logger.info(message);
    }

    public static void finalizeReports() {
        extent.flush();
        logger.info("Extent Reports Flushed. Check reports/ExtentReport.html");
    }
}
