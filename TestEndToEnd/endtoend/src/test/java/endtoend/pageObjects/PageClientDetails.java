package endtoend.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class PageClientDetails {

    private final WebDriver driver;
    private final static By BUTTON_ORDER_DISABLED = By.id("buttonContractDisabled_client_details_page");
    private final static By BUTTON_ORDER = By.id("buttonContract_client_details_page");
    
    public PageClientDetails(WebDriver aDriver) {
        this.driver = aDriver;
    }
    //selecciona 1 valor de la especialidad 1 y 2 de la especialidad 2
    public void selectTwoOptionsSpeciality_1_2(String speciality1, String speciality2) {
    	By selectSpeciality1 = By.id(speciality1);
    	By selectSpeciality2 = By.id(speciality2);
    	WebElement elementSpeciality1 = this.driver.findElement(selectSpeciality1);
    	WebElement elementSpeciality2 = this.driver.findElement(selectSpeciality2);
    	Select drpSpeciality1 = new Select(elementSpeciality1);
    	drpSpeciality1.selectByValue("1");
    	Select drpSpeciality2 = new Select(elementSpeciality2);
    	drpSpeciality2.selectByValue("2");    	
    }
    
    public void selectAvailability(String availability) {
    	By selectAvailability = By.xpath("//*[@id='table_schedule']/descendant:: input[contains(@value,'/"+availability+"/')]"); 
    	WebElement element = this.driver.findElement(selectAvailability);
    	element.click();
    }
    
    public void buttonOrderClick() {
    	WebElement element = this.driver.findElement(BUTTON_ORDER);
    	element.click();
    }
    
    public boolean buttonOrderDisabledIsVisible() {
    	WebElement element = this.driver.findElement(BUTTON_ORDER_DISABLED);
    	return element.isDisplayed();
    }
}
