
package Selenium1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Setup.utils;

public class main {

	public static void main(String[] args) throws InterruptedException { 
    //1.Define selected Browser Driver
        WebDriver driver = new ChromeDriver();

        
   //2.get Browser URL
        driver.get(utils.webUrl);
        driver.getTitle();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(20));
        
   //3. Ending
       Thread.sleep(10000);
	   driver.quit();
	}
	


}
