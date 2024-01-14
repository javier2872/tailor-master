package endtoend.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageMain {

    private final WebDriver driver;
    private final static By BUTTON_CLIENT = By.id("buttonClient_main_page");
    private final static By BUTTON_TAILOR = By.id("button-login-tailor");
    private final static By FIELD_ID_TAILOR = By.id("idTailor_modal_login");
    private final static By BUTTON_IN = By.id("buttonSubmit_modal_login");
    
    public PageMain(WebDriver aDriver) {
        this.driver = aDriver;
    }
    
    public void buttonClientClick() {
    	WebElement element = this.driver.findElement(BUTTON_CLIENT);
    	element.click();
    }
    public void buttonTailortClick() {
    	WebElement element = this.driver.findElement(BUTTON_TAILOR);
    	element.click();
    }
    public void buttonInClick() {
    	WebElement element = this.driver.findElement(BUTTON_IN);
    	element.click();
    }
    public void fillIDTailor(String idTailor) {
    	WebElement element = this.driver.findElement(FIELD_ID_TAILOR);
    	element.clear();
    	element.sendKeys(idTailor);
    }
}
