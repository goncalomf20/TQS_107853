package com.homework1.homework1.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SeleniumJupiter.class)
public class FillingTheFormTest {
  
    private WebDriver driver;
    private Map<String, Object> vars;

    @BeforeEach
    public void setUp( ) {
        this.driver = new FirefoxDriver();
        vars = new HashMap<>();
    }

  @Test
  public void fillingTheForm() {
    driver.get("http://localhost:8080/buying.html?tripId=1");
    driver.findElement(By.id("firstName")).click();
    driver.findElement(By.id("firstName")).sendKeys("Gonçalo");
    driver.findElement(By.id("lastName")).click();
    driver.findElement(By.id("lastName")).sendKeys("Ferreira");
    driver.findElement(By.id("idNumber")).click();
    driver.findElement(By.id("idNumber")).click();
    driver.findElement(By.id("idNumber")).sendKeys("123456789");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Alijó");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("2020-20");
    driver.findElement(By.cssSelector(".btn")).click();
    Alert alert = driver.switchTo().alert();
    String alertMessage = alert.getText();
    assert(alertMessage.contains("Ticket added successfully"));
    alert.accept();
    driver.close();
  }
}
