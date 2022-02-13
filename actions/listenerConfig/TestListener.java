package listenerConfig;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.nopcommerce.user.TC_Register;

public class TestListener implements ITestListener {
	
	String projectLocation = System.getProperty("user.dir"+"/screenshotImg");
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		TC_Register registerPage = new TC_Register();
		TakesScreenshot scrShot = (TakesScreenshot) registerPage.getWebdriver();
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File desFile = new File(projectLocation+result.getName()+".png");
		try {
			FileUtils.copyFile(srcFile, desFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
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

}
