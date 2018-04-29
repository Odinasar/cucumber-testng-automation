Feature: GoogleMap    
    
    Scenario: Check google map
    Given Accept type is JSON
    And parameter is "origin" : "7925 Jones Branch Driver, McLean VA 22102"
    And parameter is  "destination" : "136 Locust st, Hyannis, MA 02601"
    And  "mode" : "driving"
    And   key :  AIzaSyD-w016-gmSvMHV98t-5xBUD74OKnRQbtU
    When I perform GET request to Google maps API url: "https://maps.googleapis.com/maps/api/directions/json"
    Then status code should be 200
    And distance and duration should be in response JSON