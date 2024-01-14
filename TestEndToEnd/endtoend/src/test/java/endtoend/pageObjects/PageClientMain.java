package endtoend.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageClientMain {
    private final WebDriver driver;
    
  
    public PageClientMain(WebDriver aDriver) {
        this.driver = aDriver;
    }
    
    public boolean checkExistCard(String nameTailor) {
    	try {
    		By.xpath("//*[@id='client_Mainpage_page']/descendant:: *[contains(text(),'"+nameTailor+"')]");
        	return true;
    	}catch(Exception e) {
    		return false;
    	}    	
    }
    public void pushButtonDetails(String idTailor) {
		By cardTailor = By.xpath("//*[@id='client_Mainpage_page']/descendant:: *[@id='"+idTailor+"'] ");
		WebElement element = this.driver.findElement(cardTailor);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    	//element.click();
    }
}





