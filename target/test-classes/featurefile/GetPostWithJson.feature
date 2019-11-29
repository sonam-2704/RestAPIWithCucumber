Feature: Verify different GET and PUT Operations with locally installed json server 

#Scenario: Verify one author of the post
#Given I perform GET operation for "/posts/1"
#When I send GET HTTP request
#Then I should see the author name as "typicode"
#
#
#Scenario Outline: Verify all authors
#Given I perform GET operation for post number "<post>"
#When I send GET HTTP request
#Then I should see the title as "<title>"
#Then I should see the author name as "<authorName>"
#
#Examples: 
    #| post | title | authorName | 
    #| /posts/1 | One Indian Girl | Chetan Bhagat | 
    #| /posts/2 | AB: The Autobiography | AB de Villiers | 
    #| /posts/3 | Six Machine | Chris Gayle | 
    #| /posts/4 | Autobiography: Ace Against Odds | Sania Mirza | 
    #
    #
#Scenario: Add book title and author
#Given I set POST employee service api endpoint for "/posts"
#When I send POST HTTP request
#		| title | author |
#		| A Kingdom for his Love | Vani Mahesh and Shine Antony |
#Then I receive valid HTTP Response code 201
#And I receive valid response as
#		| title | author |
#		| A Kingdom for his Love | Vani Mahesh and Shine Antony |


#Scenario: Verify DELETE operation after POST
#Given I perform POST operation for "/posts" with body as
#		| id | title | author |
#		| 7  | Indian Paper Money | Razack |
#When I perform DELETE operation for "/posts/"
#		| postid |
#		|   7    |
#And I perform GET operation for "/posts/" with post number as
#		| postid |
#		|   7    |
#Then I should not see any response

#Scenario: Verify DELETE operation 
#Given I perform DELETE operation for "/posts/"
#		| postid |
#		|   7    |
#And I perform GET operation for "/posts/" with post number as
#		| postid |
#		|   7    |
#Then I should not see any response

Scenario: Verify PUT operation after POST
Given I perform POST operation for "/posts" with body as
		| id | title | author |
		| 7  | API Testing Course | ExecuteAutomation |
When I perform PUT operation for "/posts/"
	| id | title | author |
	| 7  | API Testing | ExecuteAutomation |
And I perform GET operation for "/posts/" with post number as
		| postid |
		|   7    |
Then I should see body with title as "API Testing"
