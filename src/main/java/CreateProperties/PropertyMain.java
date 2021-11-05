package CreateProperties;

import General.OpenManager;
import General.StartAndLogin;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class PropertyMain {
	
	public static boolean status = true;
	public static int position=1;
	public static WebDriver Az;
	
	@Test
	public void prop() throws InterruptedException, IOException {
		
		//Start and login(URL, Login, password)
		StartAndLogin azenzus = new StartAndLogin("https://test.covizmo.com/azenzus/");
		azenzus.loginAz("qa", "QAtest1+");
		Az = azenzus.getWebDriver();
		
		String filePath = "A:\\Aaa\\Property - Copy.xlsx";
		XlsxReaderForProperty reader = new XlsxReaderForProperty();
		reader.readXlsx(filePath);

		OpenManager.propertyManager(Az);
		try {
			do {
				inputAllData();
				position++;
			}
			while(position<XlsxReaderForProperty.getCount());
		}
		catch (Exception ignored) {}
	
		//close and create FileResult
		XlsxReaderForProperty.inputStream.close();
		filePath = filePath.replace(".xlsx", "-Result.xlsx");
		XlsxReaderForProperty.saveResult(filePath);
	}
	
	public void contextMenu() throws InterruptedException {
		WebElement grid = Az.findElement(By.xpath("//div[@eventproxy='MetadataPropertyManagerWindowListGrid_body']"));
		Actions contextMenu = new Actions(Az);
		contextMenu.contextClick(grid).perform();
		WebElement optionAdd =Az.findElement(By.xpath("//div[text()='Add']"));
		optionAdd.click();
		Thread.sleep(1000);
	}
	
	public void buttonSearch() {
		Az.findElement(By.xpath("//div[text()='Search']")).click();
	}
	
	public void buttonClose() throws InterruptedException {
		Az.findElement(By.xpath("//div[@eventproxy='isc_KBWindow_0']//div[contains(@eventproxy,'closeButton')]")).click();
		Thread.sleep(500);
	}
	
	public boolean checkIfExist() throws InterruptedException {
		String name = XlsxReaderForProperty.getName();
		WebElement fieldSearch = Az.findElement(By.xpath("//input[@name='searchCriteria']"));
		fieldSearch.clear();
		fieldSearch.sendKeys(name);
		buttonSearch();
		Thread.sleep(500);
		try {
			Az.findElement(By.xpath("//tr[@role='listitem']/td[2]/div[text()='"+name+"']"));
			return true;
		}
		catch (NoSuchElementException a){
			return false;
		}
	}
	
	public void inputAllData() throws InterruptedException, IOException {
		if (!checkIfExist()) {
			status = true;
			contextMenu();
			inputName();
			inputVersion();
			inputDesc();
			released();
			deprecated();
			defUnits();
			fieldType(XlsxReaderForProperty.getFieldType());
			try {
				dataType(XlsxReaderForProperty.getDataType());
			}
			catch (NoSuchElementException a) {
				
			}
			textAlign(XlsxReaderForProperty.getTextAlign());
			values(XlsxReaderForProperty.getValues());
			
			WebElement buttonSave = Az.findElement(By.xpath("//div[text()='Save']"));
			if (status) {
				buttonSave.click();
			}
			buttonClose();

			if (checkIfExist()) {
	       		XlsxReaderForProperty.setResult("Created");
	       		XlsxReaderForProperty.setID(getId());
	       	}
		}
		else {
			System.out.println("Property \""+XlsxReaderForProperty.getName()+"\" already exist");
			XlsxReaderForProperty.setResult("Alreay exist");
			try {
				XlsxReaderForProperty.setID(getId());
			}
			catch (Exception ignored) {
				
			}
		}
	}
	
	public void inputName() throws InterruptedException {
		WebElement fieldName = Az.findElement(By.xpath("//input[@name='txtName']"));
		fieldName.sendKeys(XlsxReaderForProperty.getName());
		Thread.sleep(500);
	}
	
	public void inputVersion() throws InterruptedException {
		WebElement fieldVertion = Az.findElement(By.xpath("//input[@name='txtVersion']"));
		fieldVertion.sendKeys(XlsxReaderForProperty.getVersion());
		Thread.sleep(500);
	}
	
	public void inputDesc() throws InterruptedException {
		WebElement fieldDesc = Az.findElement(By.xpath("//textarea[@name='txtDescription']"));
		fieldDesc.sendKeys(XlsxReaderForProperty.getDesc());
		Thread.sleep(500);
	}
	
	public void released() throws InterruptedException {
		if (XlsxReaderForProperty.released()) {
			Az.findElement(By.xpath("//label[text()='Released']/preceding-sibling::span")).click();
		}
		Thread.sleep(500);
	}	
	
	public void deprecated() throws InterruptedException {
		if (XlsxReaderForProperty.deprecated()) {
			Az.findElement(By.xpath("//label[text()='Deprecated']/preceding-sibling::span")).click();
		}
		Thread.sleep(500);
	}
	
	public void defUnits() throws InterruptedException, IOException {
		if (XlsxReaderForProperty.getDefUnits()!= "") {
			WebElement addDefUnits = Az.findElement(By.xpath("//div[text()='...']"));
			addDefUnits.click();
			Thread.sleep(1000);
			
			WebElement fieldSearch = Az.findElement(By.xpath("//div[@eventproxy='isc_UnitSelectorWindow_0']//input[@name='searchText']"));
			WebElement buttonSearch = Az.findElement(By.xpath("//div[@eventproxy='isc_UnitSelectorWindow_0']//div[text()='Search']"));
			WebElement buttonAdd = Az.findElement(By.xpath("//div[@eventproxy='isc_UnitSelectorWindow_0']//div[text()='Add >']"));
			WebElement buttonRemove = Az.findElement(By.xpath("//div[@eventproxy='isc_UnitSelectorWindow_0']//div[text()='< Remove']"));
			WebElement buttonApply = Az.findElement(By.xpath("//div[@eventproxy='isc_UnitSelectorWindow_0']//div[text()='Apply']"));
			while(true) {
				try {
					Az.findElement(By.xpath("//div[@eventproxy='UnitSelectorWindowRightListGrid_body']//tr[@aria-posinset='1']")).click();
					buttonRemove.click();
					Thread.sleep(500);
				}
				catch(NoSuchElementException a) {
					break;
				}
			}
			String units = XlsxReaderForProperty.getDefUnits();
			String [] temp = units.split("\\|");
			for(String u: temp) {
				fieldSearch.clear();
				fieldSearch.sendKeys(u);
				buttonSearch.click();
				Thread.sleep(500);
				try {
					Az.findElement(By.xpath("//div[text()='"+u+"']")).click();
				}
	            catch(NoSuchElementException  e) {
	            	XlsxReaderForProperty.setException("Unit \""+u+"\" doesn't exist");
	            	status = false;
					break;
	            }
				buttonAdd.click();
			}
			buttonApply.click();	
			Thread.sleep(500);
		}
	}
	
	public void fieldType(String fieldType) throws InterruptedException, IOException {
		WebElement comboboxFieldType = Az.findElement(By.xpath("//label[text()='Field Type']/ancestor::td/following-sibling::td//div[@class='selectItemText']"));
		comboboxFieldType.click();
		try {
			Az.findElement(By.xpath("//div[@eventproxy='isc_PickListMenu_0']//div[text()='"+fieldType+"']")).click();
		}
		catch (NoSuchElementException e1) {
			Az.findElement(By.xpath("//div[@eventproxy='isc_PickListMenu_0']//div[text()='TEXT']")).click();
			XlsxReaderForProperty.setException("Field Type is incorrect");
			status = false;
		}
		Thread.sleep(500);
	}
	
    public void dataType(String dataType) throws InterruptedException, IOException {
    	Az.findElement(By.xpath("//label[text()='Data Type']/ancestor::td/following-sibling::td//div[@class='selectItemText']")).click();
		try {
			Az.findElement(By.xpath("//div[@eventproxy='isc_PickListMenu_0']//div[text()='"+dataType+"']")).click();
		}
		catch (NoSuchElementException e) {
			Az.findElement(By.xpath("//div[@eventproxy='isc_PickListMenu_0']//div[text()='TEXT']")).click();
			XlsxReaderForProperty.setException("Data Type is incorrect");
			status = false;
		}
		Thread.sleep(500);
	}
    
    public void textAlign(String textAlign) throws InterruptedException, IOException {
    	Az.findElement(By.xpath("//label[text()='Text Align']/ancestor::td/following-sibling::td//div[@class='selectItemText']")).click();
		try {
			Az.findElement(By.xpath("//div[@eventproxy='isc_PickListMenu_0']//div[text()='"+textAlign+"']")).click();
		}
		catch (NoSuchElementException e3) {
			Az.findElement(By.xpath("//div[@eventproxy='isc_PickListMenu_0']//div[text()='LEFT']")).click();
			XlsxReaderForProperty.setException("Text Align is incorrect, LEFT selected as default");
		}
		Thread.sleep(500);
	}
    
    public void values(String[] values) throws InterruptedException {
    	if (values[0]!= null && values[0]!="") {
    		WebElement gridValues = Az.findElement(By.xpath("//div[contains(@eventproxy, 'MetaPropertyDesignerLayoutListGrid_body')]"));
    		Actions contextMenu = new Actions(Az);
           	int i = 0;
         	while(i<values.length) {
         		contextMenu.contextClick(gridValues).perform();
             	Thread.sleep(500);
             	WebElement optionAdd = Az.findElement(By.xpath("//div[contains(@eventproxy, 'isc_KBWindow')]//following-sibling::div//div[text()='Add']"));
           		optionAdd.click();
             	Thread.sleep(500);
             	WebElement inputValue = Az.findElement(By.xpath("//input[@name='value'][@type='TEXT']"));
             	WebElement buttonSave = Az.findElement(By.xpath("//div[contains(@eventproxy, 'isc_MetaPropertyValueMapWindow_0')]//div[text()='Save']"));
         	    inputValue.sendKeys(values[i]);
         	    buttonSave.click();
         	    Thread.sleep(500);
         		i++;
         	}
    	}
    }
    
    public int getId() {
    	int id  = Integer.parseInt(Az.findElement(By.xpath("//tr[@role='listitem']/td[2]/div[text()='"+XlsxReaderForProperty.getName()+"']/ancestor::tr/td[1]/div")).getText());
    	return id;
    }
}