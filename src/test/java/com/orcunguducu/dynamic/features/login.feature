Feature: Login test

Scenario: Successful login scenario
Given Navigate to login page
When User is not logged in
Then Populate login form
And Click login button

Scenario: Fail login scenario
Given Navigate to login page
When User is not logged in
Then Populate wrong login form
And Click login button with wrong form