@UILoginTest
Feature: A customer is able to successfully log in with valid credentials
  Description: The purpose of this feature is to test login functionality of shop apotheke UI

  @validLoginTest
  Scenario: Using valid credentials, a customer can successfully log in
    Given the user is on the shop apotheke login page
    When the user tries to login with valid credentials
    Then the user should be lead to successful login

  @invalidLoginTest1
  Scenario: Using invalid emailAddress and valid password will not lead to a successful login
    Given the user is on the shop apotheke login page
    When the user tries to login with invalid emailAddress and valid password
    Then the user should not be lead to successful login

  @invalidLoginTest2
  Scenario: Using valid emailAddress and invalid password will not lead to a successful login
    Given the user is on the shop apotheke login page
    When the user tries to login with valid emailAddress and invalid password
    Then the user should not be lead to successful login

  @invalidLoginTest3
  Scenario: Using invalid emailAddress and invalid password will not lead to a successful login
    Given the user is on the shop apotheke login page
    When the user tries to login with invalid emailAddress and invalid password
    Then the user should not be lead to successful login
