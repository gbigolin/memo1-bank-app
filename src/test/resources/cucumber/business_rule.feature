Feature: Verification of business rules in operations in bank accounts

  Scenario: Cannot deposit money when sum is negative
    Given Account with a balance of 200
    When Trying to deposit -100
    Then Operation should be denied due to negative sum
    And Account balance should remain 200

  Scenario: Cannot deposit money when sum is null
    Given Account with a balance of 200
    When Trying to deposit 0
    Then Operation should be denied due to null sums
    And Account balance should remain 200

  Scenario: Cannot be withdrawn money when the sum is greater than the account balance
    Given Account with a balance of 200
    When Trying to withdraw 3000
    Then Operation should be denied because you do not have that balance in the account
    And Account balance should remain 200
