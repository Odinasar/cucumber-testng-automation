Feature: Hr Application Database and UI data verification

  Background: 
    Given I am on DeptEmpPage

  Scenario: Department data UI and Database verification
    When I search for department id 20
    And I query database with sql:"SELECT department_name, manager_id, location_id from departments WHERE department_id = 20"
    Then UI data Database data must match

  Scenario: Firstname and lastname search by email_UI vs DB verification
    When I search for email "JWHALEN" to see firstname and lastname
    And I query database with sql:"SELECT first_name, last_name FROM employees WHERE email='JWHALEN'"
    Then UI data Database data must match

  
  Scenario Outline: Firstname and lastname search by email_UI vs DB verification
    When I search for email "<email>" to see firstname and lastname
    And I query database with sql:"SELECT first_name, last_name FROM employees WHERE email='<email>'"
    Then UI data Database data must match

    Examples: 
      | email  |
     # | JWALEN |
      | HBAER  |
      | ABANDA |
      | ABULL  |
   
  @tag1      
  Scenario Outline: Verify Number of employees for departmentsUI vs DB verification
    When I search for department id <departmentID> and get number of employees
    And I query database with sql:"SELECT COUNT(*) AS EMLOYEES_COUNT FROM employees WHERE department_id=<departmentID>"
    Then UI data Database data must match
   Examples:
   |departmentID|
   |50|
 #  |60|
   
