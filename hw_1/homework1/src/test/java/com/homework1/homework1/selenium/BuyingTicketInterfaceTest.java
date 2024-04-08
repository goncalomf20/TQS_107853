package com.homework1.homework1.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SeleniumJupiter.class)
public class BuyingTicketInterfaceTest {
    private WebDriver driver;
    private Map<String, Object> vars;

    @BeforeEach
    public void setUp( ) {
        this.driver = new FirefoxDriver();
        vars = new HashMap<>();
    }


  @Test
  public void buyingTicketInterface() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1920, 1053));
    driver.findElement(By.id("departureCity")).click();
    {
      WebElement dropdown = driver.findElement(By.id("departureCity"));
      dropdown.findElement(By.xpath("//option[. = 'Porto']")).click();
    }
    driver.findElement(By.cssSelector("#departureCity > option:nth-child(2)")).click();
    driver.findElement(By.id("arrivalCity")).click();
    {
      WebElement dropdown = driver.findElement(By.id("arrivalCity"));
      dropdown.findElement(By.xpath("//option[. = 'London']")).click();
    }
    driver.findElement(By.cssSelector("#arrivalCity > option:nth-child(2)")).click();
    driver.findElement(By.id("departureDate")).click();
    driver.findElement(By.id("departureDate")).click();
    driver.findElement(By.id("departureDate")).sendKeys("0002-09-01");
    driver.findElement(By.id("departureDate")).sendKeys("0020-09-01");
    driver.findElement(By.id("departureDate")).sendKeys("0202-09-01");
    driver.findElement(By.id("departureDate")).sendKeys("2021-09-01");
    driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
    driver.close();
  }
}
