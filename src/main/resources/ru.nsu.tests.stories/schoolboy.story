Scenario: Ўкольник решает задачу в которой необходимо выполнить сложение и вычитание большого количества чисел
с различным знаком

Given as schoolboy
When i calc expression 3+4+5
When i press result_button
When i subs from the result 1+2+3
When i press result_button
Then i get in result 6

Scenario: Ўкольник вычисл€ет выражение со скобками.

Given as schoolboy
When i calc expression (4 + 1) * 3 - 20 / (2 + 0.5 * 4) * 2
When i press result_button
Then i get in result 5