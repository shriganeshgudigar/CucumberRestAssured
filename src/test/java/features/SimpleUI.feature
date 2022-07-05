Feature: Dockerizing UI Test

  @UITest
  Scenario: UI Test
    Given application is up and running
    When the alert is open
    Then the user accepts the alert