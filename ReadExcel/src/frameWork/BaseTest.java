package frameWork;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class BaseTest {
	public WebDriver dr;
	public Properties pro;
	public	ExtentReports rep = ExtentMan.getInstance();
	public	ExtentTest test;
	public void openBrowser(String browser){

		if (pro==null){
			pro=new Properties();
			try
			{
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\frameWork\\config.properties");
				pro.load(fis);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println(pro.getProperty("appurl"));

		if(browser=="firefox"){
			dr = new FirefoxDriver();
		}
		else if (browser=="chrome"){
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--disable-extensions");
			System.setProperty("webdriver.chrome.driver", pro.getProperty("chrome_exe"));
			dr = new ChromeDriver(op);
		}
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	public void navigate(String URLKey){
		dr.get(pro.getProperty("appurl"));
	}
	public void TextType(String locator,String data){
		getElement(locator).sendKeys(data);
	}
	public void TextTypepass(String locator,String data){
		getElement(locator).sendKeys(data);
	}
	public void Click(String locator){
		getElement(locator).click();
	}

	public WebElement getElement(String locator){
		WebElement e= null;
		try{
			if(locator.endsWith("_xpath"))
				e=dr.findElement(By.xpath(pro.getProperty(locator)));
			else if(locator.endsWith("_id"))
				e=dr.findElement(By.id(pro.getProperty(locator)));
			else if(locator.endsWith("_name"))
				e=dr.findElement(By.name(pro.getProperty(locator)));
			else {
				reportingFail("App has not this element -- " + locator);
				Assert.fail("App has not this element -- " + locator);
			}
		}	
		catch(Exception ex){
			reportingFail(ex.getMessage());
			Assert.fail("App has not this element -- " + ex.getMessage());
			ex.printStackTrace();
		}
		return e;
	}

	/**********************Validates Test**************************/
	public boolean verifyTitle(){
		return false;
	}
	public boolean IsElementPresent(String locator){
		List<WebElement> elementList = null;
		if(locator.endsWith("_id"))
			elementList = dr.findElements(By.id(pro.getProperty(locator))); 
		else if(locator.endsWith("_name"))
			elementList = dr.findElements(By.name(pro.getProperty(locator))); 
		else if(locator.endsWith("_xpath"))
			elementList = dr.findElements(By.xpath(pro.getProperty(locator))); 
		else{
			reportingFail("App has not this element -- " + locator);
			Assert.fail("App has not this element -- " + locator);
		}
		if(elementList.size()==0)
			return false;
		else 
			return true;
	}
	public boolean ValidateText(){
		return false; 
	}

	/**********************Reporting Test**************************/

	public void reportingPass(String msg){
		test.log(LogStatus.PASS, "Reporting pass executed...");
	}

	public void reportingFail(String msg){
		test.log(LogStatus.FAIL, "Reporting FAIL executed...");
		takeScreenShot();
		Assert.fail();
	}
	public void takeScreenShot(){
		Date d=new Date();
		String fileScreenShot=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		File srcFile=((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try
		{
			FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"//repoerScreenShot//"+fileScreenShot)); 
		}
		catch(IOException exp){
			exp.printStackTrace();
		}
		test.log(LogStatus.INFO, "Screen Shot -- > " + test.addScreenCapture(System.getProperty("user.dir")+"//repoerScreenShot//"+fileScreenShot));
	}
}
