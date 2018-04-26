Feature: Bank Account

  Background:
    Given jon's account 12345678 is held at Barclays bank
    And mark's account 14141414 is held at Barclays bank

  Scenario: Depositing money
    Given the balance on jon's account is 42 GBP
    When jon deposits 1 GBP in his account
    Then the balance on jon's account should be 43 GBP

  Scenario: Being warned about overdraft limit
    Given the balance on jon's account is 3 GBP
    And jon's overdraft limit is 10 GBP
    When jon withdraws 5 GBP from his account
    Then the balance on jon's account should be -2 GBP
    And jon should receive a warning that his OD limit is £10

  Scenario: Being charged 50p a day for being overdrawn
    Given the balance on jon's account is -10 GBP
    When the bank settles its end of day accounts
    Then jon's account should have been charged 50p
    And the balance on jon's account should be -10.50 GBP

  Scenario: Being charged on several accounts for being overdrawn
    Given the balance on jon's account is -10 GBP
    And the balance on mark's account is 10 GBP
    When the bank settles its end of day accounts
    Then the balance on jon's account should be -10.50 GBP 
    And the balance on mark's account should be 10 GBP


    