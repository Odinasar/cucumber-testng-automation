Feature: Create contacts using beans

  
  Scenario: Create contact
    Given I logged in into suiteCRM
    When I save a new contact:
      | firstName | lastName | department | officePhone | cellPhone |
      | Steve     | Gates    | IT         |    43234234 | 423423423 |
    Then I should see contact information for "Steve Gates"
    
@create_contact
  Scenario Outline: 
    Given I logged in into suiteCRM
    When I save a new contact:
      | firstName   | lastName   | department   | officePhone    | cellPhone    |
      | <firstname> | <lastname> | <department> | <office_phone> | <cell_phone> |
    Then I should see contact information for "<firstname> <lastname>"

    Examples: 
      | firstname | lastname | department | office_phone | cell_phone |
      | Steve     | Gates    | IT         |     43234234 |  423423423 |
      | Bob       | Marlly   | IT         |     12312312 |   55434213 |
      | SomeBody  | Kim      | Seles      |      1232312 | 3464434213 |
      | Kitty     | White    | Seles      |       132232 |   54544313 |
