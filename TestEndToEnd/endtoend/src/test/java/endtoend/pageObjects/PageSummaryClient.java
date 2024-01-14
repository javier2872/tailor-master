package endtoend.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageSummaryClient {
    private final WebDriver driver;
    private final static By BUTTON_FINISH = By.id("buttonFinish_client_summary_page");
    private final static By BUTTON_ACCEPT = By.id("button_submit_modal_finish");    
    private final static By MODAL_FINISH = By.id("modal_finish");
    private final static By FIELD_NAME_CLIENT = By.id("nameClient_client_summary_page");
    
    private final static By TABLE_FIELD_NAME_TAILOR = By.id("nameTailor_client_summary_page");
    private final static By TABLE_FIELD_ORDER_CLIENT = By.id("orderTailor_client_summary_page");
    private final static By TABLE_FIELD_AVAILABILITY = By.id("dateTailor_client_summary_page");
    private final static By TABLE_FIELD_TOTAL = By.id("totalTailor_client_summary_page");
    
    
    public PageSummaryClient(WebDriver aDriver) {
        this.driver = aDriver;
    }
    
    public void fillNameClient(String name) {
    	WebElement element = this.driver.findElement(FIELD_NAME_CLIENT);
    	element.clear();
    	element.sendKeys(name);
    }
    
    public String getTextTableFieldNameTailor() {
    	WebElement element = this.driver.findElement(TABLE_FIELD_NAME_TAILOR);
    	return element.getText();
    }
    
    public String getTextTableFieldOrderClient() {
    	WebElement element = this.driver.findElement(TABLE_FIELD_ORDER_CLIENT);
    	return element.getText();
    }
    public String getTextTableFieldAvailabiity() {
    	WebElement element = this.driver.findElement(TABLE_FIELD_AVAILABILITY);
    	return element.getText();
    }
    public String getTextTableFieldTotal() {
    	WebElement element = this.driver.findElement(TABLE_FIELD_TOTAL);
    	return element.getText();
    }
    
    public boolean isEnabledButtonFinish() {
    	WebElement element = this.driver.findElement(BUTTON_FINISH);
    	return element.isEnabled();
    }
    public void clickButtonFinish() {
    	WebElement element = this.driver.findElement(BUTTON_FINISH);
    	element.click();
    }    
    public boolean isDisplayedModalFinish() {
    	WebElement element = this.driver.findElement(MODAL_FINISH);
    	return element.isDisplayed();
    }
    public void clickButtonFinishModal() {
    	WebElement element = this.driver.findElement(BUTTON_ACCEPT);
    	element.click();
    } 
}
