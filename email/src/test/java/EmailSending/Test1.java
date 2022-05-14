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

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Test1 {

	@Test
	public void emailSend() throws IOException, InterruptedException, AWTException {

		
		System.setProperty("webdriver.chrome.driver", "C://browserdrivers//chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		// read properties file
		Properties p = new Properties();
		FileInputStream f = new FileInputStream("D:\\email\\EmailSignIn.properties");
		p.load(f);

		// open url
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();

		driver.findElement(By.id(p.getProperty("email"))).sendKeys("apurva.mahendrakar@cogniwize.com");
		driver.findElement(By.xpath(p.getProperty("next"))).click();

		Thread.sleep(5000);

		driver.findElement(By.name(p.getProperty("pass"))).sendKeys("YSRK3EbB");
		driver.findElement(By.xpath(p.getProperty("next2"))).click();
		System.out.println("I am able to Login into Gmail Application Successfully");
		Thread.sleep(5000);
		driver.findElement(By.xpath(p.getProperty("google_apps"))).click();
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath(p.getProperty("mail"))).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		System.out.println("in default content");
		Thread.sleep(5000);
		String url1 = driver.getCurrentUrl();
		System.out.println("getcurrent url :" + url1);
		Set<String> s1 = driver.getWindowHandles();
		System.out.println("child window is " + s1);
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			System.out.println("title" + driver.switchTo().window(ChildWindow).getTitle());
			Thread.sleep(5000);

		}
		
		//driver.findElement(By.xpath(p.getProperty("nothanks"))).click();
		driver.findElement(By.cssSelector(p.getProperty("compose"))).click();
		driver.findElement(By.xpath(p.getProperty("nothanks"))).click();
		driver.findElement(By.name("to")).sendKeys("apurva.mahendrakar@cogniwize.com");
		driver.findElement(By.name("subjectbox")).sendKeys("Email Sent test");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys("Hi Apurva, I have sending email attachement using automation script...!!!!!  Thank You");
		
		//click on attach file
		driver.findElement(By.cssSelector(".a1.aaA.aMZ")).click();
		Thread.sleep(2000);
		Robot rb = new Robot();
		//copy the file path
		StringSelection  ss = new StringSelection("D:\\downloads\\0_Apurva Adhar card.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Thread.sleep(5000);
		//ctrl+v 
		rb.keyPress(KeyEvent.VK_CONTROL); // press on controlkey
		rb.keyPress(KeyEvent.VK_V);   //
		
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		
		//enter key
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		System.out.println("File Upload successfully");
		driver.findElement(By.className("btA")).click();
		System.out.println("Email sent successfully");
		Thread.sleep(2000);
	
		driver.navigate().to("https://mail.google.com/mail/u/0/#sent");
		
		Thread.sleep(3000);
		WebElement ele = driver.findElement(By.xpath("//*[@id=\"gs_lc50\"]/input[1]"));
		
		//ele.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
		//ele.sendKeys("Email Sent");
		
		boolean emailSub= driver.getPageSource().contains("Email Sent test");
		if(emailSub) {
			System.out.println("Email subject is verified successfully....Test is Passed!!!");
		}
		else {
			System.out.println("Email subject is not found....Test is Failed!!!!");
		}
		
		Thread.sleep(10000);
		
		driver.quit();
		
		//driver.findElement(By.cssSelector(".gb_A.gb_Ka.gb_f")).click();
		//driver.switchTo().frame(3);
		//driver.switchTo().frame(12);
		//driver.findElement(By.xpath("//div[@id='yDmH0d']/c-wiz/div/div/div/div/div[2]/div[4]/span/a/div")).click();
		//Thread.sleep(5000);
		//System.out.println("Sign-out successfully from Application");
		//driver.quit();
	}

}
