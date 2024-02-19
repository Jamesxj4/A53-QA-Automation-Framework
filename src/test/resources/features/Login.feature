Feature: Login Feature

  Scenario Outline: Login Success
    Given I open login page
    When I login with valid email <email> and password <password>
    Then I am logged in
    Examples:
      | email            | password      |
      | "demo@class.com" | "te$t$tudent" |

