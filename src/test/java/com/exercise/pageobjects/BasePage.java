package com.exercise.pageobjects;

import com.exercise.framework.SeleniumWrapper;
import com.exercise.helpers.BoxType;
import com.google.common.base.Stopwatch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BasePage extends SeleniumWrapper {
  WebDriver driver;
  BoxType boxType;

  By loader = By.xpath(".//*[@class='loader-parent' and @style='display:none']");
  By loadingIcon = By.id("AjaxLoader");
  public  BasePage(WebDriver driver){
    super(driver);
    this.driver=driver;
  }

  public ShoppingPage shoppingPage() {
    return  new ShoppingPage(driver);
  }

  public CartPage cartPage() {
    return  new CartPage(driver);
  }

  public void waitForPageToLoad() throws Exception {
    Stopwatch stopwatch = Stopwatch.createStarted();
    while ( !isElementDisplayed(loadingIcon) && stopwatch.elapsed().getSeconds() < 2 ) {
      Thread.sleep(100);
    }
    stopwatch.reset();
    stopwatch.start();
    while (isElementDisplayed(loadingIcon) && stopwatch.elapsed().getSeconds() < 120 ) {
      Thread.sleep(100);
    }
    stopwatch.stop();
  }
}
