package EmailSending;

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
	public void emailSend() throws IOException, InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en-us");

		System.setProperty("webdriver.chrome.driver", "C://browserdrivers//chromedriver.exe");

		WebDriver driver = new ChromeDriver(options);

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
		driver.findElement(By.name("subjectbox")).sendKeys("Email Testing- Selenium");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".Ar.Au div")).sendKeys("Hi Apurva,sending email using automation script");
		driver.findElement(By.className("btA")).click();
		System.out.println("Email sent successfully");
	
		driver.findElement(By.cssSelector(".gb_A.gb_Ka.gb_f")).click();
		Thread.sleep(2000);
		
		driver.switchTo().frame(3);
		driver.findElement(By.xpath("//div[@id='yDmH0d']/c-wiz/div/div/div/div/div[2]/div[4]/span/a/div")).click();
		
		System.out.println("Sign-out successfully from Application");

	}

}
