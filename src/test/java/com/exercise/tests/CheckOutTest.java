package com.exercise.tests;

import com.exercise.helpers.BoxType;
import com.exercise.pageobjects.ShoppingPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTest extends BaseTest {

  @Test
  public void checkOutItemsFromCart() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int itemsInCart = shoppingPage
        .setBoxType(BoxType.MediumBoxHeavyDuty)
        .setQuantity(5)
        .addToCart()
        .getCartCount();
    Assert.assertEquals(itemsInCart,5,"Failed to validate items in cart " + itemsInCart);
    shoppingPage
        .goToCart()
        .goToCheckOut()
        .waitForCheckOutPage();
  }

}

