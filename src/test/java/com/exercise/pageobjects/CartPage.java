package com.exercise.pageobjects;

import com.exercise.helpers.BoxType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {

  By cartList = By.className("basket-list");
  By itemInCart = By.className("basket-item");
  By increaseQuantity = By.className("fa-chevron-up");
  By decreaseQuantity = By.className("fa-chevron-down");
  By removeItem = By.className("basket-remove");
  By continueShopping = By.className("basket-continue");
  By checkout = By.xpath(".//*[text()='Checkout']");
  BoxType boxType;

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public CartPage setBoxType(BoxType boxType) {
    this.boxType=boxType;
    return this;
  }

  public CartPage removeItemFromCart() throws Exception {
    WebElement item = getItemInCart(this.boxType);
    WebElement removeItemButton = item.findElement(removeItem);
    clickElement(removeItemButton);
    return  this;
  }

  public  CartPage increaseQuantity(int quantity) throws  Exception{
    WebElement item = getItemInCart(this.boxType);
    WebElement increaseQuantityButton = item.findElement(increaseQuantity);
    while (quantity-- > 0) {
      clickElement(increaseQuantityButton);
    }
    return this;
  }
  public  CartPage decreaseQuantity() throws  Exception{
    WebElement item = getItemInCart(this.boxType);
    WebElement decreaseQuantityButton = item.findElement(increaseQuantity);
    clickElement(decreaseQuantityButton);
    return this;
  }

  private WebElement getItemInCart(BoxType typeOfBox) throws Exception {
    List<WebElement> items = getWebElements(cartList);
    for (int i = 0; i < items.size(); i++) {
      if (items.get(i).getText().contains(typeOfBox.getBoxTitle())) {
        return items.get(i);
      }
    }
    throw new Exception("Given item :" + typeOfBox + " not found");
  }

  public CartPage continueShopping() throws Exception {
    clickElement(continueShopping);
    return this;
  }

  public CheckOutPage goToCheckOut() throws Exception {
    clickElement(checkout);
    return new CheckOutPage(driver);
  }
}
