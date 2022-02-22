package reportConfig;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManagerV2 {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			String workingDir = System.getProperty("user.dir");
			extent = new ExtentReports(workingDir + "\\extentReport\\ExtentReportV2.html", true);
		}
		return extent;
	}
}