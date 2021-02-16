Feature: Kayak Search
 Scenario Outline: User makes a Search
    Given User goes to Kayak site
    When  User clicks on Flights link
    And User enters from "<from>" airport
    And User enters to "<to>" airport
    And User enters date "<start_date>" and "<end_date>"
    And User clicks on search button
    Then Result pane is displayed and first one "<from>" selected
    Examples:
      | from| to  | start_date  | end_date  |
      | NYC | SAN | 04/23/2021  | 06/12/2021|
      | BLR | NYC | 06/21/2021  | 07/8/2021 |
      | JFK | SFO | 05/3/2021   | 06/12/2021|