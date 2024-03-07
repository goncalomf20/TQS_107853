package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SeleniumJupiter.class)
public class Lab42RefactoredTest {
    private WebDriver driver;
    private Map<String, Object> vars;

    @BeforeEach
    public void setUp( ) {
        this.driver = new FirefoxDriver();
        vars = new HashMap<>();
    }

    @Test
    public void lab42() {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1363, 691));
        driver.findElement(By.cssSelector(".container > p:nth-child(2)")).click();
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'Rome']")).click();
        }
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        driver.findElement(By.cssSelector(".control-group:nth-child(2)")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Gonçalo Ferreira");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("Ajilĩ");
        driver.findElement(By.cssSelector(".control-group:nth-child(4) > .control-label")).click();
        driver.findElement(By.id("city")).sendKeys("Vila Real");
        driver.findElement(By.cssSelector(".control-group:nth-child(5) > .controls")).click();
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("alijó");
        driver.findElement(By.cssSelector("form")).click();
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("5070-011");
        driver.findElement(By.cssSelector(".control-group:nth-child(8) > .control-label")).click();
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("1234123412341234");
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys("2026");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("Jonçalo Maria");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}
