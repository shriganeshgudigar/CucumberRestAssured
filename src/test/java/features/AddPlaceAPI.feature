Feature: Add place API

  @apiTest
  Scenario:Verifying add place api
    Given user enters all valid request details
    When user submits add place api request
    Then response with status code "200" is recieved
    And response with status is "OK" recieved
