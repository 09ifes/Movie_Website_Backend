Feature: get a specific film


  Scenario Outline: getting a specific film from the DB

    Given a film exists with id <filmID>
    When i request a film's details
    Then the webpage should show the film's title "<title>"
    Examples:
      | filmID | title |
      | 1       | AMADEUS  |
      | 2       | Neptune  |