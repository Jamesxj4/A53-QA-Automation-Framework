Feature: Login Logout Info panel Feature

  Scenario Outline: Validate user is able to log in to Koel app with registered email and password
    Given I open login page
    When I login with valid email <email> and password <password>
    Then I am logged in
    Examples:
      | email                 | password      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |

  Scenario Outline: Navigates to profile page
    Given I open login page
    When I login with valid email <email> and password <password>
    And I click the view profile button
    Then I am in profile page
    Examples:
      | email                 | password      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |


  Scenario Outline: Validate user is able to to update password
    Given I open login page
    When I login with valid email <email> and password <password>
    And I click the view profile button
    And I provide current password <password>
    And I provide new password <newPassword>
    And I click profile save button
    Then Password updates successfully

    Examples:
      | email                 | password      |newPassword      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |"QnNBjg7512"     |

  Scenario Outline: Validate user is able to to update name
    Given I open login page
    When I login with valid email <email> and password <password>
    And I click the view profile button
    And I provide current password <password>
    And I provide new name <newName>
    And I click profile save button
    Then Profile Name updates successfully
    Examples:
      | email                 | password      |newName      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |"Student01"  |