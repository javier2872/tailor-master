package endtoend.stepsDefinitions;

import java.net.HttpURLConnection;

import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import endtoend.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hook {
    private static WebDriver driver;
    
    private static String idTailor;
    private static String  idJob;
    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver aDriver) {
        driver = aDriver;
    }

    private static HttpURLConnection con = null;

    private static JSONObject jsonResponse = null;

    public static HttpURLConnection getCon() {
        return con;
    }
    public static void setCon(HttpURLConnection aCon) {
        con = aCon;
    }

    public static JSONObject getJsonResponse() {
        return jsonResponse;
    }

    public static void setJsonResponse(JSONObject aJsonResponse) {
        jsonResponse = aJsonResponse;
    }
    
    public static void setIDTailor(String idTailorA) {
    	idTailor = idTailorA;
    }
    public static void setIDJob(String idJobA) {
    	idJob = idJobA;
    }
    
    @Before()
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        setDriver(new ChromeDriver());
        getDriver().manage().window().maximize();
        java.time.Duration timeW= java.time.Duration.ofSeconds((long)5);
        getDriver().manage().timeouts().implicitlyWait(timeW);
    }

    @After()
    public void teardown(Scenario aScenario) {
    	Utils util = new Utils();
    	if (idTailor!=null) {
    		util.deleteTailor(idTailor);
    	}
		if(idJob!=null) {
			util.deleteTailor(idJob);
		}
        if (getDriver() != null) {
            if (aScenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                aScenario.attach(screenshot, "image/png", aScenario.getName()); //stick it in the report
            }
            getDriver().quit();
        }
        if (getCon() != null) {
            getCon().disconnect();
        }
    }

}
