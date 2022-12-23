Feature: get extra details from a film


  Scenario Outline: retrieving inner joined film data from the db

    Given a film exists with the id <filmID>
    When i request a film's additional details
    Then the webpage should show the film's title "<title>" and category "<category>" and release year <release_year>
    Examples:
      | filmID  | title    | category    | release_year |
      | 1       | AMADEUS  | Documentary | 2006         |
      | 2       | Neptune  | Horror      | 2006         |