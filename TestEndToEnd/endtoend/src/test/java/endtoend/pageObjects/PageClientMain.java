package endtoend.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageClientMain {
    private final WebDriver driver;
    
  
    public PageClientMain(WebDriver aDriver) {
        this.driver = aDriver;
    }
    
    public boolean checkExistCard(String nameTailor) {
    	try {
    		By.xpath("//*[@id='client_Mainpage_page']/descendant:: *[contains(text(),'/"+nameTailor+"/')]");
        	return true;
    	}catch(Exception e) {
    		return false;
    	}    	
    }
    public void pushButtonDetails(String nameTailor) {
		By cardTailor = By.xpath("//*[@id='client_Mainpage_page']/descendant:: *[contains(text(),'/"+nameTailor+"/')]/ancestor:: *[contains(@class, 'content-card')]/descendant:: *[contains(@class, 'btn')] ");
		WebElement element = this.driver.findElement(cardTailor);
    	element.click();
    }
}





