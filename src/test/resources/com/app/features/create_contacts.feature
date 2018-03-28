Feature: Creating contacts

  Scenario: Create contact using CREARE page
    Given I logged in into suiteCRM
    And I open the create contact page
    And I enter the first name "Roma" and the last name "Kostin"
    And I enter the phone number "774-534-62-72"
    And I enter the department "IT"
    When click on the save button
    Then I should see contact information for "Roma Kostin"

  Scenario: Create contact using CREARE page
    Given I logged in into suiteCRM
    And I open the create contact page
    And I enter the first name "Temirlan" and the last name "Oralkhanov"
    And I enter the phone number "774-534-62-72"
    And I enter the department "IT"
    When click on the save button
    Then I should see contact information for "Temirlan Oralkhanov"

 # @crete_contact
  Scenario Outline: Create multiple contacts
    Given I logged in into suiteCRM
    And I open the create contact page
    And I enter the first name "<firstname>" and the last name "<lastname>"
    And I enter the phone number "<phonenumber>"
    And I enter the department "<depatment>"
    When click on the save button
    Then I should see contact information for "<firstname> <lastname>"
    Examples: 
      | firstname | lastname   | phonenumber | department |
      | Satoshi   | Nakamuro   | 98723439234 | IT         |
      | John      | Smith      |  2342342344 | Sales      |
      | Rajesh    | Yash       |    23423234 | IT         |
      | Roma      | Kostin     |   234232323 | Military   |
      | Aidar     | Rakimjanov |   236876767 | Sales      |
