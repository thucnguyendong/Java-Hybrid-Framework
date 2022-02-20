package listenerConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.BaseTest;

public class ReportNGListener implements ITestListener {
	String projectLocation = System.getProperty("user.dir")+File.separator+"reportScrShoot"+File.separator;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("---------- " + result.getName() + " PASS test ----------");
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		Object testClass = result.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getWebdriver();

		String screenshotPath = saveScreenShootAsBase64(webDriver);
		Reporter.getCurrentTestResult();
		Reporter.log("<br><a target='_blank' href=\"file:///" + screenshotPath + "\">" + "<img src=" + screenshotPath+" " + "height='100' width='150'/> " + "</a></br>");
		Reporter.setCurrentTestResult(null);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("---------- " + result.getName() + " FAILED test ----------");
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		Object testClass = result.getInstance();
		WebDriver webDriver = ((BaseTest) testClass).getWebdriver();

		String screenshotPath = captureScreenshoot(webDriver, result.getName());
		Reporter.getCurrentTestResult();
		Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
		Reporter.setCurrentTestResult(null);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	public String captureScreenshoot(WebDriver driver, String screenshotName) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			TakesScreenshot scrShot = (TakesScreenshot) driver;
			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
			String srcShootPath = projectLocation+screenshotName+"_"+formater.format(calendar.getTime())+".png";
			FileUtils.copyFile(srcFile, new File(srcShootPath));
			return srcShootPath;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String saveScreenShootAsBase64(WebDriver driver) {
		try {
			TakesScreenshot scrShot = (TakesScreenshot) driver;
			return "data:image/png;base64,"+scrShot.getScreenshotAs(OutputType.BASE64);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
