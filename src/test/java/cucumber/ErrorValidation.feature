
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative test of submitting the order
    Given I landed on Ecommerce Page
	  When Logged in with a user name <name> and password <password>
	  Then "Incorrect email or password." message is displayed

    Examples: 
      | name                  | password        |
      | vaishnavi@sankhla.com | Saini@123456781 |