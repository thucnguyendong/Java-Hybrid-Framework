package commons;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected String projectPath = System.getProperty("user.dir");
	private String chromeVersion = "93.0.4577.63";
	protected String userUrl,adminUrl;
	
	protected WebDriver getLocalBrowserDriver(String browserName) {
		if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("edge")){
			System.setProperty("webdriver.edge.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	protected WebDriver getHeadlessBrowserDriver(String browserName) {
		if (browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("headless");
			option.addArguments("window-size-1440x900");
			driver = new ChromeDriver(option);
		}
		
		else if (browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", projectPath+File.separator+"driverBrowsers"+File.separator+"geckodriver.exe");
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("-headless");
			option.addArguments("window-size-1440x900");
			driver = new FirefoxDriver(option);
		}
		
		else {
			throw new RuntimeException("Browser name invalid");
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if (browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if (browserName.equals("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browserName.equals("opera")){
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}
		else if (browserName.equals("coc coc")){
			WebDriverManager.chromedriver().driverVersion(chromeVersion).setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		}
		else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	protected void setEnvironmentURL(String environment) {
		switch (environment) {
			case "DEV":
				userUrl = GlobalConstants.USER_PORTAL_PAGE_URL;
				adminUrl = GlobalConstants.ADMIN_PAGE_URL;
				break;
			default:
				System.out.println("Wrong environment name");
		}
	}
}
