Feature: SugarCRM menu options

  
  Scenario: Verify Collaboration menu options
    Given I logged in into suiteCRM
    When I hover over the Collaboration menu
    Then following menu options should be visible for Collaboration:
      | Home      |
      | Emails    |
      | Documents |
      | Projects  |
      
   @f&f
  Scenario: Verify Collaboration menu options
    Given I logged in into suiteCRM
    When I hover over the Support menu
  #  When I hover over the Sales menu
  #  When I hover over the Marketing menu
  #  When I hover over the All menu
  #  When I hover over the Collaboration menu
  #  When I hover over the Activities menu
    Then following menu options should be visible for Support:
      | Home        |
      | Accounts    |
      | Contacts    |
      | Cases       |
      
      @f&f
  Scenario: Verify Collaboration menu options
    Given I logged in into suiteCRM
    When I hover over the Sales menu
    Then following menu options should be visible for Sales:
      | Home             |
      | Accounts         |
      | Contacts         |
      | Opportunities    |
      |Leads             |   
      
