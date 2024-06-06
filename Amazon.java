package first;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	public static WebDriver driver;


	public static void main(String[] args) throws IOException, Exception {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		driver = new EdgeDriver(options);
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement file=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		file.click();
		TakesScreenshot ts=(TakesScreenshot)driver;
		File f=ts.getScreenshotAs(OutputType.FILE);
		File f1=new File("C:\\Users\\Keerthana\\eclipse-workspace\\Selenium\\Screenshot\\Image.png");
		FileUtils.copyFile(f, f1);
		
		Select s=new Select(file);
		s.selectByVisibleText("Electronics");
		Thread.sleep(2000);
		WebElement send=driver.findElement(By.xpath("//input[@dir='auto']"));
		Actions a=new Actions(driver);
		a.sendKeys(send,"mobile 5g").perform();
	
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		
	    r.keyPress(KeyEvent.VK_ENTER);
	    r.keyRelease(KeyEvent.VK_ENTER);

	    WebElement down = driver.findElement(By.xpath("//span[text()='Previous']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", down);
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[30]")).click();
		String get = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		for(String x : set) {
			if(!x.equals(get)) {
				driver.switchTo().window(x);	
			}
		}
		driver.findElement(By.xpath("//input[@id=\"buy-now-button\"]")).click();
	}

}
