package com.exercise.pageobjects;

import com.exercise.helpers.BoxType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingPage extends BasePage {

  public ShoppingPage(WebDriver driver) {
    super(driver);
  }

  By box = By.className("boxshop-item");
  By boxTitle = By.className("boxshop-item-title");
  By boxQuantity = By.cssSelector("input.boxshop-item-input.basket-input");
  By addToCart = By.className("boxshop-item-add");
  By increaseQuantity = By.className("fa-chevron-up");
  By decreaseQuantity = By.className("fa-chevron-down");
  By cartCount = By.className("basket-count");
  By cart = By.id("basket-info");

  public ShoppingPage setBoxType(BoxType boxType) {
    this.boxType=boxType;
    return this;
  }


  private WebElement getBoxType(BoxType typeOfBox) throws Exception {
    waitForElementToDisplay(box);
    waitForPageToLoad();
    List<WebElement> boxes = getWebElements(box);
    for (int i = 0; i < boxes.size(); i++) {
      if (boxes.get(i).findElement(boxTitle).getText().equals(typeOfBox.getBoxTitle())) {
        return boxes.get(i);
      }
    }
    throw new Exception("Given box type:" + typeOfBox + " not found");
  }

  public ShoppingPage setQuantity(int quantity) throws Exception {
    WebElement boxType = getBoxType(this.boxType);
    WebElement quantityTextBox = boxType.findElement(boxQuantity);
    clearQuantity(quantityTextBox);
    setText(quantityTextBox, Integer.toString(quantity));
    return this;
  }

  private void clearQuantity(WebElement quantityElement) throws Exception{
    int count=1;
    try {
      while (getQuantity() != 0 && count++ <= 5) {
        quantityElement.clear();
      }
    }
    catch (NumberFormatException ex) {
      //It's cleared.
    }
  }

  public int getQuantity() throws Exception {
    WebElement boxType = getBoxType(this.boxType);
    WebElement quantityTextBox = boxType.findElement(boxQuantity);
    int actualQuantity = Integer.parseInt(quantityTextBox.getAttribute("value"));
    return actualQuantity;
  }

  public ShoppingPage increaseQuantity(int quantity) throws Exception {
    WebElement boxType = getBoxType(this.boxType);
    WebElement increaseQuantityButton = boxType.findElement(increaseQuantity);
    while (quantity-- > 0) {
      clickElement(increaseQuantityButton);
    }
    return this;
  }

  public ShoppingPage decreaseQuantity(int quantity) throws Exception {
    WebElement boxType = getBoxType(this.boxType);
    WebElement decreaseQuantityButton = boxType.findElement(decreaseQuantity);
    clickElement(decreaseQuantityButton);
    return this;
  }

  public ShoppingPage addToCart() throws Exception {
    WebElement boxType = getBoxType(this.boxType);
    WebElement addToCartButton = boxType.findElement(addToCart);
    clickElement(addToCartButton);
    waitForPageToLoad();
    return this;
  }

  public int getCartCount() throws Exception {
    int itemsInCart = Integer.parseInt(getWebElementText(cartCount));
    return itemsInCart;
  }

  public ShoppingPage waitForCartToUpdate(int countBeforeRefresh) throws Exception{
    int timeOut = 5;
    while(countBeforeRefresh==getCartCount() && timeOut-->0) {
      Thread.sleep(1000);
    }
    return  this;
  }

  public ShoppingPage waitForShoppingPage() throws Exception{
    waitForElementToDisplay(box);
    return this;
  }

  public CartPage goToCart() throws Exception{
    clickElement(cart);
    waitForPageToLoad();
    return new CartPage(this.driver);
  }
}
