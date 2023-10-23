Feature:Bank account operations
  Checking bank transactions

  Scenario: Successfully transaction money when balance is enough
    Given Account with a balance of 1000
    When Trying to withdraw 500
    When Trying to deposit 1000
    When  Trying to deposit 10
    Then Account balance should be 1510

  Scenario: Promotion successfully applied in transaction
    Given Account with a balance of 0
    When Trying to deposit 1000
    When Trying to withdraw 300
    When Trying to withdraw 200
    When Trying to deposit 3000
    Then Account balance should be 3800

  Scenario: Cannot transaction when deposit sum is null
    Given Account with a balance of 200
    When Trying to deposit 1100
    When Trying to withdraw 300
    When Trying to deposit 1500
    When Trying to deposit 0
    Then Operation should be denied due to null sums
    And Account balance should remain 2500

  Scenario: Cannot transaction when deposit sum is invalid
    Given Account with a balance of 1000
    When Trying to withdraw 300
    When Trying to deposit -800
    Then Operation should be denied due to negative sum
    And Account balance should remain 700

  Scenario: transaction cannot be made when the amount to be withdrawn is greater than the account balance
    Given Account with a balance of 1000
    When Trying to withdraw 300
    When Trying to deposit 800
    When Trying to withdraw 5000
    Then Operation should be denied because you do not have that balance in the account
    And Account balance should remain 1500


