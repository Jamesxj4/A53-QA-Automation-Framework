Feature: CreateSmartPlaylist Feature

  As a user, I want to create a Smart playlist in app, so that I can enjoy the music and modify my settings and preferences

  Scenario Outline: User can create a Smart playlist with one rule
    Given I open login page
    When I login with valid email <email> and password <password>
    And I click Create Playlist button
    When I select Create Smart Playlist option
    And I input playlist name "<smartPlaylistName>"
    And I choose rule one Title contains "<text>"
    And I click Save button
    Then Smart playlist "<smartPlaylistName>" is created
    Examples:
      | email            | password      | smartPlaylistName | text |
      | "demo@class.com" | "te$t$tudent" | Smart Playlist    | a    |