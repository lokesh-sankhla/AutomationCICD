package rahulshettyacademy.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorvalidation() throws IOException {
		landingPage.loginApplication("vaishnavi@sankhla1.com", "Saini@123456781");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test(retryAnalyzer = Retry.class)
	public void productErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("selenium@testrun.com", "Selenium@12345678");

		// Product Page
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		// cart page
		Assert.assertTrue(cartPage.verifyProductDisplay("ADIDAS ORIGINALS"));
		
	}

}
