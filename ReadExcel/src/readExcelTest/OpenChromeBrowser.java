package readExcelTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenChromeBrowser {

	public static void main(String[] args) {
	     ChromeOptions op = new ChromeOptions();
	     op.addArguments("--disable-extensions");
	     System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//chrome//chromedriver.exe");
	     WebDriver dr = new ChromeDriver(op);
	     dr.manage().window().maximize();
	     dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	     dr.get("http://google.com/");
	     System.out.println(dr.getTitle());
	     dr.quit();
	}

}
