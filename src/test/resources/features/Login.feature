Feature: Login Logout Info panel Feature

  Scenario Outline: Validate user is able to log in to Koel app with registered email and password
    Given I open login page
    When I login with valid email <email> and password <password>
    Then I am logged in
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
    And I update password <newPassword> to <password>
    Examples:
      | email                 | password      |newPassword      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |"QnNBjg75$$12"   |

  Scenario Outline: Validate user is able to to update password and change password back
    Given I open login page
    When I login with valid email <email> and password <password>
    And I update password <password> to <newPassword>
    And I update password <newPassword> to <password>
    Examples:
      | email                 | password      |newPassword      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |"QnNBjg75$$12"   |

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

  Scenario Outline: Validate user is able to to log out after valid login
    Given I open login page
    When I login with valid email <email> and password <password>
    And I click log out button
    Then I am not logged in
    Examples:
      | email                 | password      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |

  Scenario Outline: Validate user is able to to log out after updating name
    Given I open login page
    When I login with valid email <email> and password <password>
    And I click the view profile button
    And I provide current password <password>
    And I provide new name <newName>
    And I click profile save button
    And I click notification
    And I click log out button
    Then I am not logged in v2
    Examples:
      | email                 | password      |newName      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |"Student01"  |

  Scenario Outline: Validate user is able to to log out after updating password
    Given I open login page
    When I login with valid email <email> and password <password>
    And I click the view profile button
    And I provide current password <password>
    And I provide new password <newPassword>
    And I click profile save button
    And I click notification
    And I update password <newPassword> to <password>
    And I click notification
    And I click log out button
    Then I am not logged in v2
    Examples:
      | email                 | password      |newPassword      |
      | "james.lu@testpro.io" | "QnNBjg75$$"  |"QnNBjg75$$12"   |

  Scenario: Validate user is not able to to log in with empty email and password
    Given I open login page
    When I login with empty email and password
    Then I am not logged in v0

  Scenario Outline: Validate user is not able to to log in with valid email and invalid password
    Given I open login page
    When I login with valid email <email> and password <password>
    Then I am not logged in v0
    Examples:
      | email                 | password       |
      | "james.lu@testpro.io" | "qwer1234!@#$" |

