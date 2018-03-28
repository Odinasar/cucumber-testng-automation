Feature: Note on Dashboard

  Background: 
    Given I logged in into suiteCRM
@01
  Scenario: Post a note on Dasgboard
    When I post "Hello, 10X team!"
    Then Post "Hello, 10X team!" should be displayed
    Then I logout from app
@01
  Scenario: Post a another note on Dasgboard
    When I post "Hello World!"
    Then Post "Hello World!" should be displayed
    Then I logout from app
