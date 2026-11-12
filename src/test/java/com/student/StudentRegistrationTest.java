
package com.student;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentRegistrationTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        String path = new File("webpage/registration.html")
                .getAbsolutePath();

        driver.get("file:///" + path);
    }

    @Test
    public void successfulRegistration() {

        driver.findElement(By.id("name"))
                .sendKeys("Rahul Kumar");

        driver.findElement(By.id("email"))
                .sendKeys("rahul@gmail.com");

        driver.findElement(By.id("usn"))
                .sendKeys("4MC24AI001");

        Select dept = new Select(
                driver.findElement(By.id("department")));

        dept.selectByVisibleText("AIML");

        driver.findElement(By.xpath("//button[@type='submit']"))
                .click();

        String message = driver.findElement(By.id("message"))
                .getText();

        Assert.assertEquals(message,
                "Registration Successful!");
    }

    @Test
    public void emptyFieldsValidation() {

        driver.findElement(By.xpath("//button[@type='submit']"))
                .click();

        String message = driver.findElement(By.id("message"))
                .getText();

        Assert.assertEquals(message,
                "Please fill all fields!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
