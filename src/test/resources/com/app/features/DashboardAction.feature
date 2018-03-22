Feature: Note on Dashboard
@testing
  Scenario: Post a note on Dasgboard
    Given I logged in into suiteCRM
    When I post "Hello, 10X team!"
    Then Post "Hello, 10X team!" should be displayed
    Then I logout from app
@modul @Regression
  Scenario: Post a another note on Dasgboard
    Given I logged in into suiteCRM
    When I post "Hello World!"
    Then Post "Hello World!" should be displayed
    Then I logout from app
