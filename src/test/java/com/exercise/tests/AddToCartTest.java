package com.exercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.exercise.helpers.BoxType;
import com.exercise.pageobjects.ShoppingPage;


public class AddToCartTest extends BaseTest {

  @Test
  public void addItemsToCart() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int itemsInCart = shoppingPage
        .setBoxType(BoxType.MediumBoxHeavyDuty)
        .setQuantity(5)
        .addToCart()
        .getCartCount();
    Assert.assertEquals(itemsInCart,5,"Failed to validate items in cart " + itemsInCart);
  }


  @Test
  public void removeItemsFromCartWhenThereIsOnlyOne() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int itemsInCart = shoppingPage
        .setBoxType(BoxType.JumboBoxHeavyDuty)
        .setQuantity(2)
        .addToCart()
        .getCartCount();
    Assert.assertEquals(itemsInCart, 2, "Failed to validate items in cart " + itemsInCart);

   shoppingPage
        .goToCart()
        .setBoxType(BoxType.JumboBoxHeavyDuty)
        .removeItemFromCart()
        .shoppingPage()
        .waitForShoppingPage();
  }

  @Test
  public void removeItemsFromCartWhenThereAreMoreThanOne() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int itemsInCart= shoppingPage
        .setBoxType(BoxType.JumboBoxHeavyDuty)
        .setQuantity(2)
        .addToCart()
        .setBoxType(BoxType.MediumBoxHeavyDuty)
        .setQuantity(2)
        .addToCart()
        .getCartCount();
    Assert.assertEquals(itemsInCart,4,"Failed to validate items in cart " + itemsInCart);

  int cartCount = shoppingPage
        .goToCart()
        .setBoxType(BoxType.JumboBoxHeavyDuty)
        .removeItemFromCart()
        .shoppingPage()
        .getCartCount();
  int countAfterRemove= shoppingPage
      .waitForCartToUpdate(cartCount)
      .getCartCount();
    Assert.assertEquals(countAfterRemove,2,"Failed to validate items in cart " + countAfterRemove);
  }
}

