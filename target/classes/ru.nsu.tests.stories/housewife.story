Scenario: Домохозяйка проводит подсчет потраченных средств по чеку.

Given as housewife
When i buy 6 items for 2 ruble
When i press result_button
Then i get in result 12
When i click reset
When i buy 1 items for 10 ruble
When i press result_button
Then i get in result 10
When i click reset
When i calculate check sum
When i press result_button
Then i get in result 22