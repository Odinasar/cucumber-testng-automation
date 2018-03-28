Feature: Creating contacts

  
  Scenario: Create contact using a map
    Given I logged in into suiteCRM
    When I create a new contact:
      | first_name   | John      |
      | last_name    | Smith     |
      | office_phone | 232023232 |
      | cell_phone   | 232323233 |
    Then I should see contact information for "John Smith"
  
  Scenario Outline: Create contact using a map
    Given I logged in into suiteCRM
    When I create a new contact:
      | first_name   | <first_name>   |
      | last_name    | <l_name>       |
      | office_phone | <office_phone> |
      | cell_phone   | <cell_phone>   |
    Then I should see contact information for "<first_name> <l_name>"
    Examples: 
      | first_name | l_name  | cell_phone | office_phone |
      | Michael    | Jackson |  123123123 |      3234234 |
      | Bonnie     | Garcia  |    2342342 |        24234 |
      | Adem       | Rasshad |   12323432 |       234234 |
