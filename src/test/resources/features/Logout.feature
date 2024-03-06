Feature: Logout Feature

Background:
  Given I open login page
  And I login with email "james.lu@testpro.io" and password "QnNBjg75$$"

  Scenario: Validate log out button
    Then Log out button is existed

  Scenario: Validate user is able to to log out
    When I click logout button
    Then I am not logged in

