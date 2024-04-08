package com.homework1.homework1.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(SeleniumJupiter.class)
public class CheckTicketTestTest {
  
    private WebDriver driver;
    private Map<String, Object> vars;

    @BeforeEach
    public void setUp( ) {
        this.driver = new FirefoxDriver();
        vars = new HashMap<>();
    }

  
  @Test
  public void checkTicketTest() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1363, 692));
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.id("token")).click();
    driver.findElement(By.id("token")).sendKeys("T-TEST123");
    driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
    driver.findElement(By.id("ticket-details")).click();
    driver.findElement(By.cssSelector("button")).click();
    driver.close();
  }
}
