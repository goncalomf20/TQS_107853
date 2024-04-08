@cache_sample
Feature: Cache Service With Cucumber

  Scenario: Put value into cache
    Given a key "testKey"
    And a value with key "currency" and value "USD"
    When I put the value into the cache with key "testKey"
    Then the value should be retrievable from the cache with key "testKey"

  Scenario: Clear cache
    Given a key "testKey"
    And a value with key "currency" and value "USD"
    When I put the value into the cache with key "testKey"
    And I clear the cache
    Then the value should not be retrievable from the cache with key "testKey"

  Scenario: Retrieve currencies from cache
    Given a key "currencies"
    And a value with key "USD" and value "1.0"
    And a value with key "EUR" and value "0.85"
    And a value with key "GBP" and value "0.75"
    When I get all values from the cache
    Then the value should be retrievable from the cache with key "currencies"
    And the value should not be retrievable from the cache with key "unknown_key"
