package com.exercise.tests;

import com.exercise.helpers.BoxType;
import com.exercise.pageobjects.ShoppingPage;

import org.testng.Assert;
import org.testng.annotations.Test;


public class ChangeQuantityTest extends BaseTest{


  @Test
  public void changeQuantityOfBox() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int quantitySet = shoppingPage
        .setBoxType(BoxType.MediumBoxHeavyDuty)
        .setQuantity(5)
        .getQuantity();
    Assert.assertEquals(quantitySet,5);
  }
  @Test
  public void increasesQuantityOfBox() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int quantitySet = shoppingPage
        .setBoxType(BoxType.JumboBoxHeavyDuty)
        .increaseQuantity(1)
        .getQuantity();
    Assert.assertEquals(quantitySet,2);
  }

  @Test
  public void decreasesQuantityOfBox() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int quantitySet = shoppingPage
        .setBoxType(BoxType.JumboBoxHeavyDuty)
        .setQuantity(2)
        .decreaseQuantity(1)
        .getQuantity();
    Assert.assertEquals(quantitySet,1);
  }
  @Test
  public void setInvalidQuantity() throws Exception {
    ShoppingPage shoppingPage = new ShoppingPage(driver);
    int quantitySet = shoppingPage
        .setBoxType(BoxType.JumboBoxHeavyDuty)
        .setQuantity(-1)
        .getQuantity();
    Assert.assertNotEquals(quantitySet,-1,"Failed to validate quanity " + -1);
  }
}

