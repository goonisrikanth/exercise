package com.exercise.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Stopwatch;

import java.util.List;

public class SeleniumWrapper {
  protected WebDriver driver;
  protected JavascriptExecutor js;


  public SeleniumWrapper(WebDriver driver) {
    this.driver = driver;
    this.js = (JavascriptExecutor) driver;
  }

  protected void clickElement(By by) throws Exception {
    WebElement element = getWebElement(by);
    waitForElementToClick(element);
    element.click();
  }

  protected void clickElement(WebElement element) throws Exception {
    waitForElementToClick(element);
    element.click();
  }

  private void waitForElementToClick(WebElement element) throws Exception {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  protected WebElement getWebElement(By by) throws Exception {
    waitForElementToDisplay(by);
    return driver.findElement(by);
  }

  protected List<WebElement> getWebElements(By by) throws Exception {
    return driver.findElements(by);
  }

  protected void waitForElementToDisplay(By by) throws Exception {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.ignoring(NoSuchElementException.class);
    wait.ignoring(StaleElementReferenceException.class);
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  protected void waitForElementToDisplay(WebElement element) throws Exception {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.ignoring(NoSuchElementException.class);
    wait.ignoring(StaleElementReferenceException.class);
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  protected boolean isElementDisplayed(By by) throws Exception {
    return driver.findElement(by).isDisplayed();
  }

  protected boolean isElementPresent(By by) throws Exception {
    return driver.findElements(by).size() > 0;
  }

  protected void waitForElementPresence(By by) throws Exception {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.ignoring(NoSuchElementException.class);
    wait.ignoring(StaleElementReferenceException.class);
    wait.until(ExpectedConditions.presenceOfElementLocated(by));
  }


  protected void setText(By by, String text) throws Exception {
    WebElement element = getWebElement(by);
    element.sendKeys(text);
  }

  protected void setText(WebElement element, String text) throws Exception {
    waitForElementToDisplay(element);
    element.clear();
    element.sendKeys(text);
  }

  protected String getWebElementText(By by) throws Exception {
    WebElement element = getWebElement(by);
    return element.getText();
  }

  protected String getWebElementText(WebElement element) throws Exception {
    return element.getText();
  }
}
