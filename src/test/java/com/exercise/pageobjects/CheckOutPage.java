package com.exercise.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage {

  By breadCrumb = By.className("boxshop-breadcrumbs");
  public CheckOutPage(WebDriver driver) {
    super(driver);
  }

  public CheckOutPage waitForCheckOutPage() throws Exception {
    waitForElementToDisplay(breadCrumb);
    return this;
  }
}
