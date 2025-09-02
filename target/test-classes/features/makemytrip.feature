#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Search for Round Trip Flights on MakeMyTrip

  Scenario: Verify Round Trip flight search from HYD to MAA
    Given I launch the Chrome browser
    And I open the MakeMyTrip website
    And I close the initial modal if present
    When I select Round Trip option
    And I enter From location as "HYD" and To location as "MAA"
    And I select departure date as "7" and return date as "9"
    And I click on the Search button
    Then I should see the search results page