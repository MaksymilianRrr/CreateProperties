package General;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class OpenManager {
	
	public static void contactManager(WebDriver Az) throws InterruptedException {
		Az.findElement(By.xpath("//td[text() = 'Search']")).click();
        Az.findElement(By.xpath("//div[contains(text(), 'Contact')]")).click();
        Thread.sleep(1000);
	}
	
	public static void metadataManager(WebDriver Az, String type, String type2) throws InterruptedException {
		Az.findElement(By.xpath("//td[text()='Data Manager']")).click();
	    Thread.sleep(500);
	    Az.findElement(By.xpath("//*[text()='Metadata']")).click();
	    Thread.sleep(500);
	    Az.findElement(By.xpath("//div[contains(text(), '"+type+"')]")).click();
	    Thread.sleep(500);
	    if (!Objects.equals(type, "Equipment")) {
	    	Az.findElement(By.xpath("//div[contains(text(), '"+type2+"')][@role='presentation']")).click();
	    }
	    Thread.sleep(1000);
	}
	
	public static void propertyManager(WebDriver Az)  {
		Az.findElement(By.xpath("//td[text()='Data Manager']")).click();
	    Az.findElement(By.xpath("//*[text()='Metadata']")).click();
	    Az.findElement(By.xpath("//*[text()='Property']")).click();
	}
	
	public static void metaImportManager(WebDriver Az) {
		Az.findElement(By.xpath("//td[text() = 'Tools']")).click();
	    Az.findElement(By.xpath("//div[contains(text(), 'Import Meta')]")).click();
	}
	
	public static void dymanicImportManager(WebDriver Az) throws InterruptedException {
		Thread.sleep(1000);
		Az.findElement(By.xpath("//td[text() = 'Tools']")).click();
        Az.findElement(By.xpath("//div[contains(text(), 'Import (dynamic)')]")).click();
        Thread.sleep(1000);
	}
	//type: Product/Function/Other
	public static void nounAndModifierManager(WebDriver Az, String type) {
		Az.findElement(By.xpath("//td[contains(text(), 'Data Manager')]")).click();
        Az.findElement(By.xpath("//*[contains(text(), 'N&M Manager')]")).click();
        Az.findElement(By.xpath("//div[contains(text(), '"+type+"')]")).click();
	}
	
	public static void masterdataManager(WebDriver Az, String type) throws InterruptedException {
		Az.findElement(By.xpath("//td[contains(text(), 'Data Manager')]")).click();
        Az.findElement(By.xpath("//*[text()='Masterdata']")).click();
        Az.findElement(By.xpath("//div[contains(text(), '"+type+"')]")).click();
        Thread.sleep(1000);
	}
	
	public static void equipmentManager(WebDriver Az) throws InterruptedException {
		Az.findElement(By.xpath("//td[text() = 'Search']")).click();
        Az.findElement(By.xpath("//div[contains(text(), 'Equipment')]")).click();
        Thread.sleep(1000);
	}
}
//OpenManager.propertyManager(Az);