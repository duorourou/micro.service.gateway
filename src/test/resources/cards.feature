Feature: the cards can be retrieved

  Scenario: client makes call to GET /cards/{id}
    When the client calls /cards/abc
    Then the client receives status code of 200
    And the client receives card with id abc