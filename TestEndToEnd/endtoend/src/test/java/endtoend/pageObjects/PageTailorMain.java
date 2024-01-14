package endtoend.pageObjects;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageTailorMain {
	    private final WebDriver driver;
	    private final static By TABLE_DISPONIBILITY = By.xpath("//*[@id='table_availabilty_List_Features_Tailor']/descendant:: tbody/tr");
	    private final static By TABLE_JOBS= By.xpath("//*[@id='table_List_Jobs_Tailor']/descendant:: tbody/tr/th");    
	    
	    
	    public PageTailorMain(WebDriver aDriver) {
	        this.driver = aDriver;
	    }
	    
	    public boolean checkAvailability(String availability) {
	    	List<WebElement> elements = this.driver.findElements(TABLE_DISPONIBILITY);
	    	for(WebElement element :elements) {
	    		if (element.getText().contains(availability))
	    			return false;
	    	  } 	    	    	
	    	return true;
	    }
	    	    
	    public boolean checkClientName(JSONObject job, String client) {
			List<WebElement> elements = this.driver.findElements(TABLE_JOBS);	    	
			JSONArray clientJSON = (JSONArray) job.get("client");		
			JSONObject clientName = (JSONObject) clientJSON.get(0);
			return elements.get(1).getText().contains(clientName.get("name").toString());
	    }
	    public boolean checkClientDate(JSONObject job) {
			List<WebElement> elements = this.driver.findElements(TABLE_JOBS);	    	
			String date = job.get("date").toString();		
			return elements.get(3).getText().contains(date);
	    }
	    public boolean checkClientJobs(JSONObject job) {
			List<WebElement> elements = this.driver.findElements(TABLE_JOBS);
			JSONArray specialties = (JSONArray) job.get("item");
			JSONObject specialties1 = (JSONObject) specialties.get(0);
			JSONObject specialties2 = (JSONObject) specialties.get(1);
			String specialties1Name=specialties1.get("name").toString();
			String specialties2Name=specialties2.get("name").toString();
			String specialties1Number=specialties1.get("number").toString();
			String specialties2Number=specialties2.get("number").toString();
				
			return ( elements.get(2).getText().contains(specialties1Name) &&
					 elements.get(2).getText().contains(specialties2Name) &&
					 elements.get(2).getText().contains(specialties1Number) &&
					 elements.get(2).getText().contains(specialties2Number));
	    }
}
