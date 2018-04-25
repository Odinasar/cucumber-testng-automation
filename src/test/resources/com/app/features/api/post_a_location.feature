Feature: post new location

  Scenario: Post new addres
    Given Content type and Accept type are Json
    When I post new location with 4100 location_id
    Then Status code is 201
    And Response json should contain this location info


  Scenario Outline: Post new addresses
    Given Content type and Accept type are Json
    When I post new location with <id> location_id
    Then Status code is 201
    And Response json should contain this location info

    Examples: 
      | id   |
      | 3400 |
      | 3500 |
      | 3600 |
      | 3700 |
      | 3800 |
      | 3900 |
      | 4000 |

    @api-location
  Scenario Outline: Get location info
    Given Content type and Accept type are Json
    When I send get request with <id>
    Then Status code is 200
    And response json should match with:
      | location_id   | country_id   | postal_code   | state_province   | city   | street_address   |
      | <location_id> | <country_id> | <postal_code> | <state_province> | <city> | <street_address> |

    Examples: 
      | location_id | country_id | postal_code | state_province | city    | street_address | id   |
      |        3400 | US         |       02601 | MA             | Hyannis | 136 Locust st  | 3400 |
      |        3500 | US         |       02601 | MA             | Hyannis | 136 Locust st  | 3500 |
      |        3600 | US         |       02601 | MA             | Hyannis | 136 Locust st  | 3600 |
      |        3700 | US         |       02601 | MA             | Hyannis | 136 Locust st  | 3700 |
      |        3800 | US         |       02601 | MA             | Hyannis | 136 Locust st  | 3800 |
