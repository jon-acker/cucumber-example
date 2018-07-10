Feature: Some feature

  As a Customer

  Rules:

  - customer who buys two bacon sandwiches gets free coke

  Background:
    Given my basket is empty

  Scenario: Adding a sandwich to the basket
    When I add a bacon sandwich to my basket
    Then my basket should contain:
      | bacon |

  Scenario: Getting free drink when two bacon sandwiches in basked
    Given my basket contains:
      | bacon |
    When I add a bacon sandwich to my basket
    Then my basket should contain:
      | bacon |
      | bacon |
      | coke  |

  Scenario: Getting no free drink when only one bacon sandwich
    Given my basket contains:
      | bacon |
    When I add a cheese sandwich to my basket
    Then my basket should contain:
      | bacon  |
      | cheese |
