package com.homework1.homework1.cucumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homework1.homework1.services.CacheService;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CacheServiceSteps {

    @Mock
    private CacheService cacheService;

    private String key;
    private Map<String, Object> value;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("^a key \"([^\"]*)\"$")
    public void a_key(String key) {
        this.key = key;
    }

    @Given("^a value with key \"([^\"]*)\" and value \"([^\"]*)\"$")
    public void a_value_with_key_and_value(String key, String value) {
        this.value = new HashMap<>();
        this.value.put(key, value);
    }

    @When("^I put the value into the cache with key \"([^\"]*)\"$")
    public void i_put_the_value_into_the_cache_with_key(String key) {
        cacheService.putIntoCache(key, value);
    }

    @When("^I get all values from the cache$")
    public void i_get_all_values_from_the_cache() {
        cacheService.getFromCache("currencies");
    }


    @Then("^the value should be retrievable from the cache with key \"([^\"]*)\"$")
    public void the_value_should_be_retrievable_from_the_cache_with_key(String key) {
        when(cacheService.getFromCache(key)).thenReturn(value); 
        Map<String, Object> retrievedValue = cacheService.getFromCache(key);
        assertEquals(value, retrievedValue);
    }

    @Then("^the value should not be retrievable from the cache with key \"([^\"]*)\"$")
    public void the_value_should_not_be_retrievable_from_the_cache_with_key(String key) {
        when(cacheService.getFromCache(key)).thenReturn(null); 
        Map<String, Object> retrievedValue = cacheService.getFromCache(key);
        assertNull(retrievedValue);
    }

    @When("^I clear the cache$")
    public void i_clear_the_cache() {
        cacheService.clearCache();
    }
}
