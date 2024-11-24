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
// 7. by XPath (Most powerful way among the others)

//Selenium Waits Types
//1. Implicit
//2. Explicit
//3. Fluent

//Selenium WebElements Attribute Extension
// click() command
// sendKeys() Command
// getText() Command
// getAttribute() Command
// clear() Command
// isDisplayed() Command
// isEnabled() Command
// getLocation() Command
// IsSelected() Command
// submit() Command
// getSize() Command

//Selenium Asserts
// https://himanshu-sheth.medium.com/how-to-use-assert-and-verify-in-selenium-webdriver-e13480315f32
// 1. Hard Asserts stop at the point, won't continue next
// 3. Soft Asserts still continue next

// Keyboards and Mouse Methods Action Class
// Keyboard Methods
// Perform
// ESC
// TAB
// ENTER
// ARROW_DOWN
// ARROW_UP
// KEYS.DOWN
// KEYS.UP
// PAGE_DOWN
// PAGE_UP

// Mouse Methods
// click()
// doubleClick()
// clickAndHold()
// contextClick()
// dragAndDrop()
// moveToElement()
// moveByOffset()
// release()

package selenium5;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import Setup.utils;

public class main3 {

	WebDriver driver;
	Assertion hardAssert = new Assertion();
	Assertion softAssert = new SoftAssert();


	@Test
	@Parameters("browser")
	public void main(String browser) throws Exception {

		// 1.Define selected Browser Driver
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			options.setEnableDownloads(true);
			options.addArguments("--start-maximized");
			//options.addArguments("--incognito");
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
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20));

		// 3. Web Elements with Keyboards Methods
		Actions ac = new Actions(driver);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Johan");

		int position = 10;
		driver.findElement(By.xpath("//select[@id='country']")).click();
		for(int i = 0; i <= position; i++){
			ac.sendKeys(Keys.DOWN).build().perform();//press down arrow key
		}
		ac.sendKeys(Keys.ENTER).build().perform();//press enter

		// 4. Web Elements with Mouse Methods

		//Drag and Drop
		//WebElement on which drag and drop operation needs to be performed
		//WebElement fromElement = driver.findElement(By Locator of fromElement);

		//WebElement to which the above object is dropped
		//		WebElement toElement = driver.findElement(By Locator of toElement);

		//Creating object of Actions class to build composite actions
		//		Actions builder = new Actions(driver);

		//Building a drag and drop action
		//		Action dragAndDrop = builder.clickAndHold(fromElement)
		//				.moveToElement(toElement)
		//				.release(toElement)
		//				.build();

		//Performing the drag and drop action
		//		dragAndDrop.perform();
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(driver.findElement(By.xpath("//div[@id='draggable']")))
				.moveToElement(driver.findElement(By.xpath("//div[@id='droppable']")))
				.release(driver.findElement(By.xpath("//div[@id='droppable']")))
				.build();
		dragAndDrop.perform();

		// 5. Web Elements with Pen Methods
		// Full Example: https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/java/src/test/java/dev/selenium/actions_api/PenTest.java#L26-L33
		//
		// WebElement pointerArea = driver.findElement(By.id("pointerArea"));
		// new Actions(driver)
		// 		.setActivePointer(PointerInput.Kind.PEN, "default pen")
		// 		.moveToElement(pointerArea)
		// 		.clickAndHold()
		// 		.moveByOffset(2, 2)
		// 		.release()
		// 		.perform();
		//
		// 6. Web Elements with Wheel Methods
		// Full Example: https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk/examples/java/src/test/java/dev/selenium/actions_api/WheelTest.java#L17-L20
		// WebElement iframe = driver.findElement(By.tagName("iframe"));
		// new Actions(driver)
		//		.scrollToElement(iframe)
		//		.perform();
		//
		driver.findElement(By.xpath("//input[@id='comboBox']")).sendKeys("Item 3");
		//driver.findElement(By.xpath("//input[@id='comboBox']")).click();
		//ac.scrollToElement(driver.findElement(By.xpath("//input[@id='comboBox']"))).perform();
		//ac.click().perform();
		// 7. Ending
		// Thread.sleep(10000);
		// driver.quit();
	}

}