package endtoend.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageTailorMain {
	    private final WebDriver driver;
	    private final static By TABLE_DISPONIBILITY = By.xpath("//*[@id='table_availabilty_List_Features_Tailor']/descendant:: tbody/tr");
	    private final static By TABLE_JOBS= By.xpath("//*[@id='table_List_Jobs_Tailor']/descendant:: tbody/tr");    
	    
	    
	    public PageTailorMain(WebDriver aDriver) {
	        this.driver = aDriver;
	    }
	    
	    public List<String> checkAvailability(String availability) {
	    	List<String> listAvailability=null;
	    	List<WebElement> elements = this.driver.findElements(TABLE_DISPONIBILITY);
	    	for(WebElement element :elements) {
	    		listAvailability.add(element.getText());
	    	  } 	    	    	
	    	return listAvailability;
	    }
	    	    
	    public boolean checkJobs(String nameClient, String fecha, string <String>job) {
	    	List<WebElement> element = this.driver.findElements(TABLE_JOBS);
	    }
}
