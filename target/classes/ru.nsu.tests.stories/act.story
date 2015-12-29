Scenario: Acceptance Scenario using UI keys

Given Start page of Online Calculator in browser
When i type sum of 6 and 2 using UI keys
When i press result_button
Then i get in result 8

Given Start page of Online Calculator in browser
When i type sub of 6 and 2 using UI keys
When i press result_button
Then i get in result 4

Given Start page of Online Calculator in browser
When i type mul of 6 and 2 using UI keys
When i press result_button
Then i get in result 12

Given Start page of Online Calculator in browser
When i type div of 6 and 2 using UI keys
When i press result_button
Then i get in result 3

Scenario: Acceptance Scenario using keyboard

Given Start page of Online Calculator in browser
When i type sum of 6 and 2 using keyboard
When i press result_button
Then i get in result 8

Given Start page of Online Calculator in browser
When i type sub of 6 and 2 using keyboard
When i press result_button
Then i get in result 4

Given Start page of Online Calculator in browser
When i type mul of 6 and 2 using keyboard
When i press result_button
Then i get in result 12

Given Start page of Online Calculator in browser
When i type div of 6 and 2 using keyboard
When i press result_button
Then i get in result 3


