
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background: 
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with a user name <name> and password <password>
	  When I add product <productname> from cart
	  And Checkout <productname> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name                  | password       |  productname     |
      | vaishnavi@sankhla.com | Saini@12345678 |  ADIDAS ORIGINAL |
