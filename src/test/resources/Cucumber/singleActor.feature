Feature: get a specific actor


  Scenario Outline: getting a specific actor from the DB

    Given an actor exists with id <actorID>
    When i request an actor's details
    Then the webpage should show the actor's firstname "<firstName>" and lastname "<lastname>"
    Examples:
      | actorID | firstName | lastname     |
      | 1       | PENELOPE  | GUINESS      |
      | 5       | JOHNNY    | LOLLOBRIGIDA |