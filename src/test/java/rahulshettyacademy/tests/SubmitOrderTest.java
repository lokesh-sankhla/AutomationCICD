package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

		String countryName = "India";

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Product Page
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		// cart page
		Assert.assertTrue(cartPage.verifyProductDisplay(input.get("product")));
		CheckoutPage checkoutpage = cartPage.goToCheckout();

		// checkout page
		checkoutpage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutpage.submitOrder();

		// confirmation Page
		Assert.assertTrue(confirmationPage.verifyConfirmationMessage().equalsIgnoreCase("Thankyou for the order."));
	}

	// To verify ZARA COAT 3 is displayed in order page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("vaishnavi@sankhla.com", "Saini@12345678");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	

	// Extent Reports

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJasonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email","vaishnavi@sankhla.com");
//	map.put("password", "Saini@12345678");
//	map.put("product", "IPHONE 13 PRO");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email","selenium@testrun.com");
//	map1.put("password", "Selenium@12345678");
//	map1.put("product", "ADIDAS ORIGINAL");
}