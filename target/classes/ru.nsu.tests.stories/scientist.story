Scenario: Ученый использует выражение в котором числа превышают вместимость поля для ввода.

Given as scientist
When i calc expression 100000000000*100000000000 using UI keys
When i press result_button
When i div from the result 1000000000000
When i press result_button
Then i get in result 10000000000

Scenario: Ученый производит вычисления с бесконечностью.

Given as scientist
When i calc not standard expression Infinity * - Infinity
When i press result_button
Then i get not standard in result -Infinity