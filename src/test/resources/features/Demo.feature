Feature: Demo
  As a User,
  Google searching should work on all platforms

  @C001 @smokeTest @regression
  Scenario: Appium homepage can be found within search results
    Given I am on "http://www.google.com"
    When I search for "appium"
    And select "Appium" in the search results
    Then the User views the Appium screen "http://appium.io/"

    