# language: en
Feature: Swag Labs Login Functionality

  As a user, I want to be able to log in to the application
  to access its features.

  Scenario Outline: Failed login attempts
    Given I open the "<browser>" browser and go to the login page
    When I enter the username "<username>"
    And I enter the password "<password>"
    And I click the login button
    Then I must see the error message "<errorMessage>"

    Examples:
      | browser | username        | password      | errorMessage                                       |
      | firefox |                 | secret_sauce  | Epic sadface: Username is required                 |
      | chrome  |                 | secret_sauce  | Epic sadface: Username is required                 |
      | firefox | standard_user   |               | Epic sadface: Password is required                 |
      | chrome  | standard_user   |               | Epic sadface: Password is required                 |
      | firefox | locked_out_user | secret_sauce  | Epic sadface: Sorry, this user has been locked out.|
      | chrome  | locked_out_user | secret_sauce  | Epic sadface: Sorry, this user has been locked out.|
      | firefox |                 |               | Epic sadface: Username is required                 |
      | chrome  |                 |               | Epic sadface: Username is required                 |
  Scenario Outline: Successful Login
    Given I open the "<browser>" browser and go to the login page
    When I log in with the user "standard_user" and the password "secret_sauce"
    Then I must be redirected to the products page

    Examples:
      | browser |
      | firefox |
      | chrome  |

  Scenario Outline: Add all items to cart and verify counter
    Given I open the "<browser>" browser and go to the login page
    When I log in with the user "standard_user" and the password "secrete_sauce"
    And I add 3 products to the cart
    Then The cart Counter must show 3

    Examples:
      | browser |
      | firefox |
      | chrome  |