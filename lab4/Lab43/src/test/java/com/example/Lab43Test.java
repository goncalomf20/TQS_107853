package com.example;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SeleniumJupiter.class)  
class Lab43Test{

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
 driver.get(
            "https://bonigarcia.dev/selenium-webdriver-java/login-form.html");

    driver.findElement(By.id("username")).sendKeys("user"); 
    driver.findElement(By.id("password")).sendKeys("user"); 
    driver.findElement(By.cssSelector("button")).click(); 

    assertThat(driver.findElement(By.id("success")).isDisplayed()).isTrue(); 
    }

}