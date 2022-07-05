Feature:

  @webTest
  Scenario: verify login credentials
    Given username "abc" and password "pwd" and application url
    When user enters valid credentials
    |shriganesh|Gudigar|Chavatti|
    |Nagendra  |Naik   |Chavatti|
    Then user logs in to the application successfully