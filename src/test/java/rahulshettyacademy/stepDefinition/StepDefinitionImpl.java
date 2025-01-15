package rahulshettyacademy.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	String countryName = "India";
	public ConfirmationPage confirmationPage;
	public CartPage cartPage;
	public CheckoutPage checkoutpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
	landingPage = 	launchApplication();
	}
	
	@Given ("^Logged in with a user name (.+) and password (.+)$")
	public void looged_in_username_and_password(String username,  String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) from cart$")
	public void I_add_product_to_cart(String product) {
		productCatalogue.addProductToCart(product);
	}
	
	@And ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String product) {
		cartPage = productCatalogue.goToCartPage();

		// cart page
		Assert.assertTrue(cartPage.verifyProductDisplay(product));
		checkoutpage = cartPage.goToCheckout();

		// checkout page
		checkoutpage.selectCountry(countryName);
		confirmationPage = checkoutpage.submitOrder();
	}
	
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displyed_confirmationPage(String string) {
		Assert.assertTrue(confirmationPage.verifyConfirmationMessage().equalsIgnoreCase(string));
		driver.quit();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string) {
		Assert.assertEquals(landingPage.getErrorMessage(), string);
		driver.close();
	}
	
}
