// Please look at this references for browser options

// https://stackoverflow.com/questions/51844033/how-to-correctly-use-firefoxoptions-and-its-arguments-to-pass-it-to-the-firefox
// https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/firefox/FirefoxOptions.html
// https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/chrome/ChromeOptions.html
// https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/edge/EdgeOptions.html
// https://www.selenium.dev/documentation/webdriver/browsers/safari/
// https://www.selenium.dev/selenium/docs/api/py/webdriver_ie/selenium.webdriver.ie.options.html

// Selenium Web Elements Types
// 1. By ID
// 2. By Name/CssSelector
// 3. By ClassName
// 4. By TagName
// 5. By Link Text 
// 6. Partial Link Text
// 7. by XPath (Most powerfull way among the others)

//Selenium Waits Types
//1. Implicit
//2. Explicit
//3. Fluent

package selenium3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Setup.utils;

public class main1 {

	WebDriver driver;

	@SuppressWarnings("deprecation")
	@Test
	@Parameters("browser")
	public void main(String browser) throws Exception {

		// 1.Define selected Browser Driver
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.setEnableDownloads(true);
			options.addArguments("--start-maximized");
			options.addArguments("--incognito");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-search-engine-choice-screen");
			options.addArguments(
					"--disable-features=OptimizationGuideModelDownloading,OptimizationHintsFetching,OptimizationTargetPrediction,OptimizationHints");
			// options.addArguments("--headless"); // for github CLI/linux/others without
			// GUI
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			// options.addArguments("-headless");
			// options.addArguments("--kiosk"); //Open Browser in maximized mode no close
			// button
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--start-maximized");
			options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
			driver = new EdgeDriver(options);
		} else if (browser.equalsIgnoreCase("ie")) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.getBrowserName();
			driver = new InternetExplorerDriver(options);
		}

		// 2.get Browser URL
		driver.get(utils.webUrl);
		driver.getTitle();
		// driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		// 3. Web Elements

		// Web Element by ID
		WebElement UserName = wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("name"))));
		UserName.sendKeys("Johan");
		System.out.println(UserName.getAccessibleName());
		System.out.println(UserName.getAttribute(browser));
		System.out.println(UserName.isDisplayed());
		System.out.println(UserName.isEnabled());
		System.out.println(UserName.getLocation());
		System.out.println(UserName.getTagName());
		System.out.println(UserName.getSize());

		WebElement UserEmail = driver.findElement(By.id("email"));
		UserEmail.sendKeys("johan@johan.com");
		System.out.println(UserEmail.getAccessibleName());
		System.out.println(UserEmail.getAttribute(browser));
		System.out.println(UserEmail.isDisplayed());
		System.out.println(UserEmail.isEnabled());
		System.out.println(UserEmail.getLocation());
		System.out.println(UserEmail.getTagName());
		System.out.println(UserEmail.getSize());

		// Web Element by Name/cssSelector
		WebElement LabelAutomationTesting = driver.findElement(By.cssSelector("title"));
		System.out.println(LabelAutomationTesting.getText());

		// Web Element by Class Name
		WebElement TitleName = wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("entry-title"))));
		System.out.println(TitleName.getText());

		// Web Element by tagName
		WebElement StartTag = wait.until(ExpectedConditions.visibilityOfElementLocated((By.tagName("button"))));
		System.out.println(StartTag.getText());

		// Web Element by Link Text
		WebElement Errorcode = wait
				.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Errorcode 400"))));
		System.out.println(Errorcode.getText());

		// Web Element by partial Link Text
		WebElement ErrorCode400 = wait
				.until(ExpectedConditions.visibilityOfElementLocated((By.partialLinkText("400"))));
		System.out.println(ErrorCode400.getText());

		// Web Element by XPath
		WebElement UserPhone = wait
				.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@id='phone']"))));
		UserPhone.sendKeys("0812345678");

		// 4. Ending
		Thread.sleep(10000);
		// driver.quit();
	}

}