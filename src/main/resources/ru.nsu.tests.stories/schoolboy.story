Scenario: �������� ������ ������ � ������� ���������� ��������� �������� � ��������� �������� ���������� �����
� ��������� ������

Given as schoolboy
When i calc expression 3+4+5
When i press result_button
When i subs from the result 1+2+3
When i press result_button
Then i get in result 6

Scenario: �������� ��������� ��������� �� ��������.

Given as schoolboy
When i calc expression (4 + 1) * 3 - 20 / (2 + 0.5 * 4) * 2
When i press result_button
Then i get in result 5