package General;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class StartAndLogin {

	private static WebDriver Az;
	
	public StartAndLogin(String url) {
		System.setProperty("webdriver.chrome.driver", "A:\\chromedriver.exe");
        Az = new ChromeDriver();
        Az.manage().window().setPosition(new Point(1500,10));
        Az.manage().window().maximize(); 
        Az.get(url);
	}
	
	public WebDriver getWebDriver() {
		return Az;
	}
	
	public void loginAz(String loginValue, String passwordValue) throws InterruptedException {
		Thread.sleep(2000);
		WebElement login = Az.findElement(By.xpath("//input[@name='loginField']"));
	    WebElement password = Az.findElement(By.xpath("//input[@name='passwordField']"));
	    WebElement buttonLogin;
	    try {
	    	buttonLogin = Az.findElement(By.xpath("//div[text()='Login']"));
	    }
	    catch (NoSuchElementException e) {
	    	buttonLogin = Az.findElement(By.xpath("//td[text()='Login']"));
	    }
	    login.sendKeys(loginValue);
	    password.sendKeys(passwordValue);
	    buttonLogin.click();
	    System.out.println("User \""+loginValue+"\" logined successfully");
	    Thread.sleep(6000);
	}
	
	public void loginDmm(String loginValue, String passwordValue) throws InterruptedException {
		WebElement login = Az.findElement(By.xpath("//input[@name='username']"));
	    WebElement password = Az.findElement(By.xpath("//input[@name='password']"));
	    WebElement buttonLogin = Az.findElement(By.xpath("//span[text()='Login']/ancestor::a"));
	    
	    login.sendKeys(loginValue);
	    password.sendKeys(passwordValue);
	    Thread.sleep(1000);
	    buttonLogin.click();
	    Thread.sleep(6000);
	    System.out.println("User \""+loginValue+"\" logined successfully");
	}
	
	public void loginDmmInspector(String loginValue, String passwordValue) throws InterruptedException {
		WebElement login = Az.findElement(By.xpath("//input[@name='loginField']"));
	    WebElement password = Az.findElement(By.xpath("//input[@name='passwordField']"));
	    WebElement buttonLogin = Az.findElement(By.xpath("//div[text()='Login']"));
	    
	    login.sendKeys(loginValue);
	    password.sendKeys(passwordValue);
	    Thread.sleep(1000);
	    buttonLogin.click();
	    Thread.sleep(6000);
	    System.out.println("User \""+loginValue+"\" logined successfully");
	}
	
	public void loginHermes(String loginValue, String passwordValue) throws InterruptedException {
		WebElement login = Az.findElement(By.xpath("//input[@id='Username']"));
	    WebElement password = Az.findElement(By.xpath("//input[@id='Password']"));
	    WebElement buttonLogin = Az.findElement(By.xpath("//button[text()='Log In']"));
	    
	    login.sendKeys(loginValue);
	    password.sendKeys(passwordValue);
	    Thread.sleep(1000);
	    buttonLogin.click();
	    Thread.sleep(1000);
	    System.out.println("User \""+loginValue+"\" logined successfully");
	}
	
	public void loginKibana(String loginValue, String passwordValue) throws InterruptedException {
		Thread.sleep(3500);
		WebElement login = Az.findElement(By.xpath("//input[@name='username']"));
		WebElement password = Az.findElement(By.xpath("//input[@name='password']"));
	    WebElement buttonLogin = Az.findElement(By.xpath("//button[@type='submit']"));
	    
	    login.sendKeys(loginValue);
	    password.sendKeys(passwordValue);
	    Thread.sleep(1000);
	    buttonLogin.click();
	    Thread.sleep(1000);
	    System.out.println("User \""+loginValue+"\" logined successfully");
	}
	
	public void reloadPage(String url) throws InterruptedException {
		Az.navigate().refresh();
		Az.get(url);
		Thread.sleep(4000);
	}
	
	public void reloadPage() throws InterruptedException {
		Az.navigate().refresh();
		Thread.sleep(4000);
	}
}