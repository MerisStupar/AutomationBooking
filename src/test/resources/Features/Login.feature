Feature: Booking Managment Login Functionality

  Scenario Outline: Check login is successful with valid credentials
    Given user is on the login page
    When user enters <username> and <password>
    And clicks on login button
    Then user is navigated to the home page

    Examples: 
      | username | password |
      | admin    | password |

  Scenario Outline: Check login is unsuccessful with invalid credentials
    Given user is on the login page
    When user enters <username> and <password>
    And clicks on login button
    Then user remains on the login page and cannot proceed to application

    Examples: 
      | username			 | password 			|
      | admin_wrong    | password_wrong |