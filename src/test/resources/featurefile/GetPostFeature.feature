Feature: Verify different GET and PUT Operations

Scenario: Verify Status Code
Given I set GET employee service api endpoint
When I set request header
And I send GET HTTP request
Then I receive valid HTTP Response code 200


Scenario: Verify Employee Record for Id Number 2
Given I perform GET operation for "/users/2"
And I send GET HTTP request
Then I should see the first name as "Janet"
And I should see the last name as "Weaver"
And I should see the id as "2"
And I should see the email as "janet.weaver@reqres.in"

Scenario: Verify Employee Record through CSV
Given I perform GET operation for "/users/2"
And I send GET HTTP request
Then I validate the "First_Name" from CSV
And I validate the "Last_Name" from CSV
And I validate the "ID" from CSV
And I validate the "Email" from CSV

Scenario Outline: Verify Employee Record using scenario outline
Given I perform GET operation for "/users/2"
And I send GET HTTP request
Then I validate first name as "<firstName>"
Then I validate last name as "<lastName>"
Then I validate id as "<id>"
Then I validate email as "<email>"


Examples: 
    | firstName | lastName | id | email |
    | Janet | Weaver | 2 | janet.weaver@reqres.in |
   
    
Scenario: Add Employee Record
Given I set POST employee service api endpoint
When I send POST HTTP request
Then I receive valid HTTP Response code 201
And I receive valid response

