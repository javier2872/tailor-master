package endtoend.stepsDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import endtoend.utils.Utils;
import endtoend.pageObjects.PageMain;
import endtoend.pageObjects.PageClientMain;
import endtoend.pageObjects.PageClientDetails;
import endtoend.pageObjects.PageSummaryClient;
import endtoend.pageObjects.PageTailorMain;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCreateAJob {
	
	private WebDriver driver;
	JSONObject TailorCreated;
	String clientName="Comprador";
	String specialties1Name="";
	String specialties2Name="";
	String specialties1Price="";
	String specialties2Price="";
	String availability="";
	
	@Given("I create a test tailor")
	public void i_create_a_test_tailor() throws ParseException {
		Utils util = new Utils();
		String response = util.createATailor();
		Assert.assertTrue(response.length()>0 && response!=null);	
		TailorCreated=  (JSONObject) new JSONParser().parse(response); 
		Hook.setIDTailor(TailorCreated.get("id").toString());
	}
	@Given("I navigate to {string}")
	public void i_navigate_to(String url) {
		this.driver = Hook.getDriver();
        this.driver.get(url);
	}
	@When("I push on {string} in the {string}")
	public void i_push_on_in_the(String button, String page) {
		PageMain pageMain = new PageMain(Hook.getDriver());
		PageClientDetails pageClientDetails = new PageClientDetails(Hook.getDriver());
		PageSummaryClient pageSummaryClient = new PageSummaryClient(Hook.getDriver());
		
		switch(button) {
		  case "Cliente":
		  {
			  if (page.contains("login_page")) {
				  pageMain.buttonClientClick();
			  }
			  break;
		  }
		  case "Contratar Servicios":
		  {
			  if (page.contains("client_details_page")) {
				  pageClientDetails.buttonOrderClick();
			  }
			  break;
		  }
		  case "Finalizar":
		  {
			  if (page.contains("client_summary_page")) {
				  pageSummaryClient.clickButtonFinish();
			  }
			  break;
		  }
		  case "Aceptar":
		  {
			  if (page.contains("popup_client_summary_page")) {
				  pageSummaryClient.clickButtonFinishModal();
			  }
			  break;
		  }
		  case "Sastre":
		  {
			  if (page.contains("login_page")) {
				  pageMain.buttonTailortClick();
			  }
			  break;
		  }
		  case "Entrar":
		  {
			  if (page.contains("login_page")) {
				  pageMain.buttonInClick();
			  }
			  break;
		  }
		}
	}
	@When("I select the created tailor of test")
	public void i_select_the_created_tailor_of_test() throws FileNotFoundException, IOException, ParseException {
		PageClientMain pageClientMain = new PageClientMain(Hook.getDriver());
		Assert.assertTrue(pageClientMain.checkExistCard(TailorCreated.get("name").toString()));
		pageClientMain.pushButtonDetails(TailorCreated.get("id").toString());
	}
	@When("I select two specialities")
	public void i_select_two_specialities() {
		PageClientDetails pageClientDetails = new PageClientDetails(Hook.getDriver());
		Assert.assertTrue(pageClientDetails.buttonOrderDisabledIsVisible());
		JSONArray specialties = (JSONArray) TailorCreated.get("specialties");
		JSONObject specialties1 = (JSONObject) specialties.get(0);
		JSONObject specialties2 = (JSONObject) specialties.get(1);
		specialties1Name=specialties1.get("name").toString();
		specialties2Name=specialties2.get("name").toString();
		specialties1Price=specialties1.get("price").toString();
		specialties2Price=specialties2.get("price").toString();
		pageClientDetails.selectTwoOptionsSpeciality_1_2(specialties1Name, specialties2Name);
		Assert.assertTrue(pageClientDetails.buttonOrderDisabledIsVisible());
	}
	@When("I select one availability")
	public void i_select_one_availability() {
		PageClientDetails pageClientDetails = new PageClientDetails(Hook.getDriver());
		Assert.assertTrue(pageClientDetails.buttonOrderDisabledIsVisible());
		JSONArray availabilityJSON = (JSONArray) TailorCreated.get("availability");		
		availability = availabilityJSON.get(0).toString();
		pageClientDetails.selectAvailability(availability);
		Assert.assertTrue(pageClientDetails.buttonOrderIsVisible());
	}
	@When("I see the summary of the order")
	public void i_see_the_summary_of_the_order() {
		PageSummaryClient pageSummaryClient = new PageSummaryClient(Hook.getDriver());
		Assert.assertFalse(pageSummaryClient.isEnabledButtonFinish());
		Assert.assertTrue(pageSummaryClient.getTextTableFieldNameTailor().contains(TailorCreated.get("name").toString()));
		Assert.assertTrue(pageSummaryClient.getTextTableFieldOrderClient().contains(specialties1Name));
		Assert.assertTrue(pageSummaryClient.getTextTableFieldOrderClient().contains(specialties2Name));
		Assert.assertTrue(pageSummaryClient.getTextTableFieldOrderClient().contains(specialties1Price));
		Assert.assertTrue(pageSummaryClient.getTextTableFieldOrderClient().contains(specialties2Price));		
		Assert.assertTrue(pageSummaryClient.getTextTableFieldAvailabiity().contains(availability));
	}
	@When("I fill the field {string}")
	public void i_fill_the_field(String text) {
		PageSummaryClient pageSummaryClient = new PageSummaryClient(Hook.getDriver());
		PageMain pageMain = new PageMain(Hook.getDriver());
		if (text.contains("Nombre del cliente")) {
			pageSummaryClient.fillNameClient(clientName);
			Assert.assertTrue(pageSummaryClient.isEnabledButtonFinish());
		}
		if (text.contains("ID de Sastre")) {
			pageMain.fillIDTailor(TailorCreated.get("id").toString());
		}
	}
	@When("I see the pop-up")
	public void i_see_the_pop_up() {
		PageSummaryClient pageSummaryClient = new PageSummaryClient(Hook.getDriver());
		pageSummaryClient.isDisplayedModalFinish();
	}
	@Then("I check the tailor dont have availability")
	public void i_check_the_tailor_dont_have_availability() {		
		PageTailorMain pageTailorMain = new PageTailorMain(Hook.getDriver());
		Assert.assertTrue(pageTailorMain.checkAvailability(availability));
	}
	@Then("I check an order in the order's table")
	public void i_check_an_order_in_the_order_s_table() throws ParseException {
		PageTailorMain pageTailorMain = new PageTailorMain(Hook.getDriver());
		Utils util = new Utils();
		String job = util.getAJob(TailorCreated.get("id").toString());	
		JSONArray jobArray=  (JSONArray) new JSONParser().parse(job);
		JSONObject jobJSON = (JSONObject) jobArray.get(0);
		Assert.assertTrue(pageTailorMain.checkClientName(jobJSON,clientName));
		Assert.assertTrue(pageTailorMain.checkClientDate(jobJSON));
		Assert.assertTrue(pageTailorMain.checkClientJobs(jobJSON));
		Hook.setIDJob(jobJSON.get("id").toString());
	}

}
