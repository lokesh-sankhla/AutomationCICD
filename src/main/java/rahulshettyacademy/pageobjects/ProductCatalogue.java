package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		///Initialization driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> productList = driver.findElements(By.cssSelector("[class='card-body']"));
	//PageFactory
	
	
	@FindBy(css = "[class='card-body']")
	List<WebElement> productList;
	@FindBy(css = ".ng-animating")
	WebElement spinner; 
	
	By products = By.cssSelector("[class='card-body']");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	public List<WebElement> getProductList() {
	
		waitForElementToAppear(products);
		return productList;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	
	
	
}
