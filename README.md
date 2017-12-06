# Getting Started
-----------------
- Clone the repository locally using git client.
- Go to exercise folder.
- Run `mvn clean install` to run the tests

Note:
If using IDE, please import as maven project in IDE

### US-1: As a customer, I want to be able to change the quantity of an item before adding it to my cart.
#### Acceptanc Criteria
   - As a customer,
     - I should be able to change the quantity of item befor adding to cart.
     - I should be able to change the quantify for multiple items.
     - I should not able to set the quantity less than 0.
     - I should be able to increae the quanity using increase/decrease button.
     
### US-2: As a customer, I want to be able to add products to my cart, so that I can later purchase
them.
#### Acceptanc Criteria
 - As a customer,
     - I should be able to add single item to cart with valid quantity.
     - I should be able to add multiple items to cart.

#### US-3: As a customer, I want to be able to remove a product from my cart.
#### Acceptanc Criteria

- As a customer,
     - I should be able to remove items from cart and cart count should reflect the count accordingly.
     - I should be able to continue shopping from cart page.
     
 #### US-4: As a customer, when I have products in my cart, I would like to move to the checkout so that I can complete my details
 #### Acceptanc Criteria
 
 - As a customer,
     - I should be able to checkout the item from cart and navigate to checkout details page.
