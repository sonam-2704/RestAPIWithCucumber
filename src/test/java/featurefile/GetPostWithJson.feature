Feature: Verify different GET and PUT Operations with locally installed json server 

Scenario: Verify one author of the post
Given I perform GET operation for ""
And I perform GET for the post number "1"
Then I should see the author name as ""


