package EmailSending;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 {
	WebDriver driver;

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "C://browserdrivers//chromedriver.exe");

		driver = new ChromeDriver();
	}

	@Test
	public void emailSend() throws IOException, InterruptedException, AWTException {

		// read properties file
		Properties p = new Properties();
		FileInputStream f = new FileInputStream("D:\\email\\EmailSignIn.properties");
		p.load(f);
		 driver.manage().timeouts().implicitlyWait(2,TimeUnit.MINUTES);
		// open url
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();

		
		// pass email
		driver.findElement(By.id(p.getProperty("email"))).sendKeys("apurva.mahendrakar@cogniwize.com");
		// click on next button
		driver.findElement(By.xpath(p.getProperty("next"))).click();
		
		// pass password
		driver.findElement(By.name(p.getProperty("pass"))).sendKeys("YSRK3EbB");
		// click on next button
		driver.findElement(By.xpath(p.getProperty("next2"))).click();
		System.out.println("I am able to Login into Gmail Application Successfully");
		
		// click on google apps
		driver.findElement(By.xpath(p.getProperty("google_apps"))).click();
		
		// switch to the frame
		driver.switchTo().frame(0);
		// click on Mail application
		driver.findElement(By.xpath(p.getProperty("mail"))).click();
		
		// switch to default content
		driver.switchTo().defaultContent();
		System.out.println("in default content");
		
		// get current url of web page
		String url1 = driver.getCurrentUrl();
		System.out.println("getcurrent url :" + url1);
		// handing window
		Set<String> s1 = driver.getWindowHandles();
		System.out.println("child window is " + s1);
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			System.out.println("title" + driver.switchTo().window(ChildWindow).getTitle());
			

		}

		// click on compose button
		driver.findElement(By.cssSelector(p.getProperty("compose"))).click();
		// handle notification
		driver.findElement(By.xpath(p.getProperty("nothanks"))).click();
		// email id of the person to whome you want to send email
		driver.findElement(By.name("to")).sendKeys("apurva.mahendrakar@cogniwize.com");
		// pass email subject
		driver.findElement(By.name("subjectbox")).sendKeys("Email Sent test demo");
		
		// pass email body
		driver.findElement(By.cssSelector(".Ar.Au div"))
				.sendKeys("Hi Apurva, I have sending email attachement using automation script...!!!!!, Thank You");

		// click on attach file icon
		driver.findElement(By.cssSelector(".a1.aaA.aMZ")).click();
		
		// file handling using Robot Class
		Robot rb = new Robot();
		// copy the file path
		StringSelection ss = new StringSelection("D:\\downloads\\0_Apurva Adhar card.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Thread.sleep(10000);
		// ctrl+v
		rb.keyPress(KeyEvent.VK_CONTROL); // 
		rb.keyPress(KeyEvent.VK_V); //

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// enter key
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		System.out.println("File Upload successfully");

		// click on send button
		driver.findElement(By.className("btA")).click();
		System.out.println("Email sent successfully");
		
		// navigate to sent url
		driver.navigate().to("https://mail.google.com/mail/u/0/#sent");
		
		// search email subject for email verification
		boolean emailSub = driver.getPageSource().contains("Email Sent test demo");
		if (emailSub) {
			System.out.println("Email subject is verified successfully....Test is Passed!!!");
		} else {
			System.out.println("Email subject is not found....Test is Failed!!!!");
		}

	}

	@AfterTest
	public void closeDriver() throws InterruptedException {

		
		Thread.sleep(10000);
		// close all windows
		driver.quit();
	}

}
